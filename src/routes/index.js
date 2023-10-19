import Home from '../pages/Home'
import Login from '../pages/Login'
import Regis from '../pages/Regis'

const publicRoutes = [
    { path: '/', component: Home},
    { path: '/regis', component: Regis, layout:null},
    { path: '/login', component: Login}
]

const privateRoutes = [

]

export { publicRoutes, privateRoutes }  