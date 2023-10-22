package aris.bdd.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aris.bdd.annotations.ExcludeFromInsertion;
import aris.bdd.annotations.ExcludeFromSelection;
import aris.bdd.annotations.PrimaryKey;
import aris.bdd.annotations.Table;

public class GenericDAO<T> {
	private Map<String, SelectionObjectDAO> selection = new HashMap<>();
	private Map<String, SetUpdateObjectDAO> setUpdate = new HashMap<>();
	private Class<T> currentClass = null;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		setCurrentClass((Class<T>) getClass());
	}
	
	// Setting the current class
	public GenericDAO<T> setCurrentClass(Class<T> givenClass){
		this.currentClass = givenClass;
		
		return this;
	}
	
	public String getCurrentClassName() {
		if (currentClass.isAnnotationPresent(Table.class)) {
			Table table = currentClass.getAnnotation(Table.class);
			
			return table.tableName();
		} 
		
		return currentClass.getSimpleName();
	}
	
	// Where clause selection
	public GenericDAO addToSelection(String columnName, String value, String operator) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(columnName.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + columnName + "' introuvable dans la classe: " + currentClass.getSimpleName());
		
		if (selection.size() >= 1) {
			if (operator == null) throw new Exception("La selection de '" + columnName + "' a besoin d'un operateur");
		}
		SelectionObjectDAO selected = new SelectionObjectDAO(columnName, value, fieldType, operator);
		
		selection.put(columnName, selected);
		
		return this;
	}
	
	// ----------------------------------------------------------------------------
	public GenericDAO addToSelection(String name, int value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	public GenericDAO addToSelection(String name, float value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	public GenericDAO addToSelection(String name, double value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	// ----------------------------------------------------------------------------
	
	
	public GenericDAO addToSelection(String columnName, String value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(columnName.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + columnName + "' introuvable dans la classe: " + this.getClass().getSimpleName());
		
		if (selection.size() >= 1) {
			if (operator == null) throw new Exception("La selection de '" + columnName + "' a besoin d'un operateur");
		}
		SelectionObjectDAO selected = new SelectionObjectDAO(columnName, value, fieldType, operator, comparaison);
		
		selection.put(columnName, selected);
		
		return this;
	}
	
	public GenericDAO addToSelection(String name, int value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}
	public GenericDAO addToSelection(String name, float value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}
	public GenericDAO addToSelection(String name, double value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}
	
	public void freeSelection() {
		selection.clear();
	}
	
	public GenericDAO removeFromSelection(String columnName) throws Exception {
		SelectionObjectDAO selectedColumn = selection.get(columnName);
		
		if (selectedColumn == null) throw new Exception("La colonne " + columnName + " n'est pas encore selectionnee");
		
		selection.remove(columnName);
		
		return this;
	}
	
	public String getCurrentSelection() {
		if (selection.size() == 0) return "";
		
		String selectionString = "WHERE";
		
		for (Map.Entry<String, SelectionObjectDAO> selected : selection.entrySet()) {
			SelectionObjectDAO selectedInstance = selected.getValue();
			
			selectionString += " " + selectedInstance.toString();
		}
		
		return selectionString;
	}
	
	// Update methods
	public GenericDAO addToSetUpdate(String name, String value) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(name.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + name + "' introuvable dans la classe: " + this.getClass().getSimpleName());
		
		SetUpdateObjectDAO setUpdateObject = new SetUpdateObjectDAO(name, value, fieldType);
		setUpdate.put(name, setUpdateObject);
		
		return this;
	}
	
	// ----------------------------------------------
	public GenericDAO addToSetUpdate(String name, int value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	public GenericDAO addToSetUpdate(String name, float value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	public GenericDAO addToSetUpdate(String name, double value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	// ----------------------------------------------
	
	public void freeSetUpdate() {
		setUpdate.clear();
	}
	
	public GenericDAO removeFromSetUpdate(String name) throws Exception {
		SetUpdateObjectDAO selectedColumn = setUpdate.get(name);
		
		if (selectedColumn == null) throw new Exception("La colonne " + name + " n'est pas encore selectionnee");
		
		setUpdate.remove(name);
		
		return this;
	}
	
	public String getSetUpdate() {
		String setUpdateString = "SET";
		
		int i = 0;
		for (Map.Entry<String, SetUpdateObjectDAO> update : setUpdate.entrySet()) {
			SetUpdateObjectDAO setUpdateSelected = update.getValue();
			
			setUpdateString += " " + setUpdateSelected.toString();
			
			if (i < setUpdate.size() - 1) setUpdateString += ",";
			
			i++;
		}
		
		return setUpdateString;
	}
	
	// Insert - Select - Update
	public ArrayList<T> getFromDatabase(Connection c) throws SQLException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String selectionString = "SELECT * FROM " + getCurrentClassName() + "";
		
		if (selection.size() > 0) {
			selectionString += " " + getCurrentSelection();
		}
		
		System.out.println(selectionString);
		
		ArrayList<T> databaseResults = new ArrayList<>();
		
		Statement stat = c.createStatement();
		ResultSet results = stat.executeQuery(selectionString);
		
		while (results.next()) {
			Field[] rowFields = currentClass.getDeclaredFields();
			T newObj = currentClass.getDeclaredConstructor().newInstance();
			
			for (int i = 0; i < rowFields.length; i++) {
				if (rowFields[i].isAnnotationPresent(ExcludeFromSelection.class)) continue;
				
				rowFields[i].setAccessible(true);
				
				//String fieldCapitalizedName = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
				//Method setterMethod = newObj.getClass().getDeclaredMethod("set" + fieldCapitalizedName, rowFields[i].getType());
				
				if (rowFields[i].getType() == int.class) {
					rowFields[i].set(newObj, results.getInt(rowFields[i].getName()));
				} else if (rowFields[i].getType() == double.class) {
					rowFields[i].set(newObj, results.getDouble(rowFields[i].getName()));
					//setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
				} else if (rowFields[i].getType() == String.class) {
					rowFields[i].set(newObj, results.getString(rowFields[i].getName()));
					//setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
				} else if (rowFields[i].getType() == Date.class) {
					rowFields[i].set(newObj, results.getDate(rowFields[i].getName()));
					//setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
				} else if (rowFields[i].getType() == Timestamp.class) {
					rowFields[i].set(newObj, results.getTimestamp(rowFields[i].getName()));
					//setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
				}
			}
			
			databaseResults.add(newObj);
		}
		
		return databaseResults;
	}
	
        public void updateInDatabase(Connection c) throws Exception {
            if (c == null) throw  new Exception("Connection null");
            
            // Creation de statement
            Statement stat = c.createStatement();
            
            // Requete
            String requete = "UPDATE " + getCurrentClassName() + " SET " + getSetUpdate() + " WHERE " + getCurrentSelection();
            stat.executeUpdate(requete);
        }
        
        public void deleteFromDatabase(Connection c) throws SQLException, Exception {
            if (c == null) throw new Exception("Connexion est null");
            
            // Creation de statement
            Statement stat = c.createStatement();
            
            // Creation de la requete
            String requete = "DELETE FROM " + getCurrentClassName() + " WHERE " + getCurrentSelection();
            stat.executeUpdate(requete);
        }
        
	public int saveInDatabse(Connection c) throws SQLException {
		if (c == null) return -1;
		
		Statement stat = c.createStatement();
		
		String requete = "INSERT INTO " + getCurrentClassName() + "(";
		
		Field[] objectFields = this.getClass().getDeclaredFields();
		
		for (int i = 0; i < objectFields.length; i++) {
			if (objectFields[i].isAnnotationPresent(ExcludeFromInsertion.class)) {
				if (requete.charAt(requete.length() - 1) == ',') {
					requete = requete.substring(0, requete.length() - 1);					
				}
				continue; 
			}
			
			requete += objectFields[i].getName();
			
			if (i < objectFields.length - 1) requete += ",";
		}
		
		requete += ") VALUES (";
		
		for (int i = 0; i < objectFields.length; i++) {
			if (objectFields[i].isAnnotationPresent(ExcludeFromInsertion.class)) {
				if (requete.charAt(requete.length() - 1) == ',') {
					requete = requete.substring(0, requete.length() - 1);					
				}
				continue;
			}
			
			objectFields[i].setAccessible(true);

			try {
//				String fieldCapitalizedName = objectFields[i].getName().substring(0, 1).toUpperCase() + objectFields[i].getName().substring(1);
//				Method getterMethod = this.getClass().getDeclaredMethod("get" + fieldCapitalizedName);
				
				if (objectFields[i].get(this) == null) {
					requete+= "NULL";
				} else {
					if (objectFields[i].getType() == int.class) {
						requete += objectFields[i].get(this);
					} if (objectFields[i].getType() == String.class) {
						requete += "\'" + objectFields[i].get(this) + "\'";
						
//						requete += "\'" + getterMethod.invoke(this) + "\'";
					} if (objectFields[i].getType() == double.class) {
						requete += objectFields[i].get(this);
					} if (objectFields[i].getType() == Date.class) {
						String valeur = new SimpleDateFormat("dd-MM-YYYY").format(((Date) objectFields[i].get(this)));
						requete += "'" + valeur + "'";
					} if (objectFields[i].getType() == Timestamp.class) {
						String valeur = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Timestamp) objectFields[i].get(this)));
						requete += "'" + valeur + "'";
					} 
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (i < objectFields.length - 1) requete += ",";
		}
		
		requete += ")";
		
		for (int i = 0; i < objectFields.length; i++) {
			if (objectFields[i].isAnnotationPresent(PrimaryKey.class)) {
				requete += " returning " + objectFields[i].getName();
			}
		}
		
		try {
			stat.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
