import Home from "../pages/Home";
import Login from "../pages/Login";
import Planing from "../pages/Planing";
import Regis from "../pages/Regis";
import PlaningDetail from "../pages/PlaningDetail";
import Blog from "../pages/Blog";
import BlogList from "../pages/BlogList";
import UserHome from "../pages/UserHome";
import UserCart from "../pages/UserCart";
const publicRoutes = [
  { path: "/", component: Home },
  { path: "/regis", component: Regis, layout: null },
  { path: "/login", component: Login },
  { path: "/planing", component: Planing },
  { path: "/planing-detail", component: PlaningDetail },
  { path: "/planing-detail/:planingId", component: PlaningDetail },
  { path: "/blog", component: Blog },
  { path: "/blog-list", component: BlogList },
  { path: "/user", component: UserHome },
  { path: "/cart", component:UserCart},
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
