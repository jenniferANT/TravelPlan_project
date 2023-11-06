<<<<<<< HEAD
import Home from "../pages/Home";
import Login from "../pages/Login";
import Planing from "../pages/Planing";
import Regis from "../pages/Regis";
import PlaningDetail from "../pages/PlaningDetail";
import Blog from "../pages/Blog";
import BlogList from "../pages/BlogList";
const publicRoutes = [
  { path: "/", component: Home },
  { path: "/regis", component: Regis, layout: null },
  { path: "/login", component: Login },
  { path: "/planing", component: Planing },
  { path: "/planing-detail", component: PlaningDetail },
  { path: "/blog", component: Blog },
  { path: "/blog-list", component:BlogList}
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
=======
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
>>>>>>> 062b74f5846dd7cc9d5c50abb6f0d5a82ccaa7e5
