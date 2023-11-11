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
