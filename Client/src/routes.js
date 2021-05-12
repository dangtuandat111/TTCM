import Home from "pages/Home.js";
import Export from "pages/Export_Products";
import Import from "pages/Import_Products";
import Product from "pages/Products";
import Supplier from "pages/Supplier";
import Store from "pages/Stores";
import Login from "pages/Login"
const dashboardRoutes = [
  {
    path: "/home",
    name: "Home",
    icon: "fas fa-home",
    component: Home,
    layout: "/admin",
  },
  {
    path: "/products",
    name: "Product",
    icon: "fas fa-clipboard-list",
    component: Product,
    layout: "/admin",
  },
  {
    path: "/export-products",
    name: "Export Product",
    icon: "fas fa-truck",
    component: Export,
    layout: "/admin",
  },
  {
    path: "/import-product",
    name: "Import Product",
    icon: "fas fa-box-open",
    component: Import,
    layout: "/admin",
  },
  {
    path: "/supplier",
    name: "Supplier",
    icon: "fas fa-industry",
    component: Supplier,
    layout: "/admin",
  },
  {
    path: "/stores",
    name: "Stores",
    icon: "fas fa-store",
    component: Store,
    layout: "/admin",
  },
  {
    path: "/",
    name: "Logout",
    icon: "fas fa-sign-out-alt",
    component: Login,
    layout: "/login",
  },
];

export default dashboardRoutes;
