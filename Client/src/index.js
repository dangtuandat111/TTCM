import React from "react";
import ReactDOM from "react-dom";

import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./assets/css/animate.min.css";
import "./assets/scss/light-bootstrap-dashboard-react.scss?v=2.0.0";
import "./assets/css/demo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "./assets/css/style.css"
import AdminLayout from "layouts/Admin.js";
import AuthLayout from "layouts/Auth"
ReactDOM.render
(
  <BrowserRouter>
    <Switch>
      <Route  path="/admin" render={(props) => <AdminLayout {...props} />} />
      <Route path="/login" render={(props) => <AuthLayout {...props} />} />
      <Redirect from="/" to="/login"/>
    </Switch>
  </BrowserRouter>,
  document.getElementById("root")
);
