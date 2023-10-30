import { Router, useHistory, Link } from "react-router-dom";
import fbIcon from "./img/fb-icon.png";
import insICon from "./img/ins-icon.png";
import twitterIcon from "./img/twitter-icon.png";
import logo from "./img/Union.png"
import "./footer.scss"

function Footer() {
    return (
        <footer className="footer">
        <div className="footer-left">
          <div className="left-logo-container">
            <img className="left-logo" src={logo} />
            <p>TravelPlan</p>
          </div>
          <p className="left-text">Enjoy the touring with TravelPlan</p>
          {/* <p>with TravelPlan</p> */}
          <div className="left-icon-container">
            <img src={fbIcon} />
            <img src={insICon} />
            <img src={twitterIcon} />
          </div>
        </div>
        <div className="footer-right">
          <div className="footer-right-item">
            <h3 className="footer-right-heading">Resources</h3>
            <Link className="footer-right-detail">Download</Link>
            <Link className="footer-right-detail">Help Center</Link>
            <Link className="footer-right-detail">Guide book</Link>
            <Link className="footer-right-detail">App Directory</Link>
          </div>
          <div className="footer-right-item">
            <h3 className="footer-right-heading">Travellers</h3>
            <Link className="footer-right-detail">Why Travellers</Link>
            <Link className="footer-right-detail">Enterprice</Link>
            <Link className="footer-right-detail">Customer Stories</Link>
            <Link className="footer-right-detail">Instagram post</Link>
          </div>
          <div className="footer-right-item">
            <h3 className="footer-right-heading">Company</h3>
            <Link className="footer-right-detail">Travelling</Link>
            <Link className="footer-right-detail">About Locato</Link>
            <Link className="footer-right-detail">Success</Link>
            <Link className="footer-right-detail">Infomation</Link>
          </div>
          <div className="footer-right-item ">
            <h3 className="footer-right-heading">Get App</h3>
            <Link className="footer-right-detail">App Strore</Link>
            <Link className="footer-right-detail margin-bottom-55">Google Play Store</Link>
          </div>
        </div>
      </footer>
    )
}

export default Footer