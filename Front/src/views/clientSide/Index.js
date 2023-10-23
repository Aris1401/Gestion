import React from 'react'
import { CheckIfLoggedIn } from 'src/components/auth/CheckAuth'

const Index = () => {
  CheckIfLoggedIn();

  return (
    <div>Index</div>
  )
}

export default Index