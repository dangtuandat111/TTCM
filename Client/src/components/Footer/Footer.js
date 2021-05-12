import React, { Component } from "react";
import { Container } from "react-bootstrap";

class Footer extends Component {
  render() {
    return (
      <footer className="footer px-0 px-lg-3">
        <Container fluid>
          <nav>
            <ul className="footer-menu">
              <li>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  Home
                </a>
              </li>
              <li>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  Export Products
                </a>
              </li>
              <li>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  Import Products
                </a>
              </li>
              <li>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  Store
                </a>
              </li>
              <li>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  Supplier
                </a>
              </li>
            </ul>
            <p className="copyright text-center">
              © {new Date().getFullYear()}{" "}

            </p>
          </nav>
        </Container>
      </footer>
    );
  }
}

export default Footer;
