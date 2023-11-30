import Home from "../pages/Home";
import Login from "../pages/Login";
import Planing from "../pages/Planing";
import Regis from "../pages/Regis";
import PlaningDetail from "../pages/PlaningDetail";
import UserHome from "../pages/UserHome";
import UserCart from "../pages/UserCart";
import UserHistory from "../pages/UserHistory";
import Places from "../pages/Places";
import PlacesList from "../pages/PlacesList";

const publicRoutes = [
  { path: "/", component: Home },
  { path: "/regis", component: Regis, layout: null },
  { path: "/login", component: Login },
  { path: "/planing", component: Planing },
  { path: "/planing-detail/:id", component: PlaningDetail },
  { path: "/places/:id", component: Places },
  { path: "/places", component: PlacesList },
  { path: "/user", component: UserHome },
  { path: "/cart", component: UserCart },
  { path: "/history", component: UserHistory },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
