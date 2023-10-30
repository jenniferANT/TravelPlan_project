import Home from '../pages/Home'
import Login from '../pages/Login'
import Planing from '../pages/Planing'
import Regis from '../pages/Regis'

const publicRoutes = [
    { path: '/', component: Home},
    { path: '/regis', component: Regis, layout:null},
    { path: '/login', component: Login},
    { path: '/planing', component: Planing}
]

const privateRoutes = [

]

export { publicRoutes, privateRoutes }  