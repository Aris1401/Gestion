import { useEffect } from "react"
import { useNavigate } from "react-router-dom"

const { makeRequest } = require("../utility/Api")

const CheckAuth = async () => {
    return new Promise((resolve, reject) => {
        let compte = {
            valide: 0
        }

        makeRequest({
            url: 'CheckLogin',
            requestType: 'GET',
            successCallback: (data) => {
                compte = data;
                resolve(compte)
            }, 
            failureCallback: (error) => {
                alert(error)
            }
        })
    }) 
}

const RedirectToAccessedPage = () => {
    let navigate = useNavigate()
    useEffect(() => {
        CheckAuth().then((compte) => {
          if (compte !== null) {
            if (compte.profil === 1) return navigate("/acceuil")
            else return navigate("/")
          }
        })
    }, [])
}

const CheckPageAuthority = () => {
    // let navigate = useNavigate()
    // useEffect(() => {
    //     CheckAuth().then((compte) => {
    //       if (compte !== null) {
    //         if (compte.profil === 1) return navigate("/acceuil")
    //       }
    //     })
    // }, [])
}

export { CheckAuth, RedirectToAccessedPage, CheckPageAuthority };