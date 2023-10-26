import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom' //Switch renders only one component at a time
// import ShortUrlComponent from './components/ShortUrlComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { AddUrlForm } from './components/AddUrlForm';
import { ShowShortUrls } from './components/ShowShortUrls';
import { Register } from './components/Register';

function App() { // App.js is the root component for React
  return (
    <div>
      <Router>
        <HeaderComponent />
          <div className="container d-flex justify-content-center">
            <Routes>
              <Route path = "/" element = {
                <div>
                  <AddUrlForm />
                </div>}>
              </Route>
              <Route path = "/-/show" element = {<ShowShortUrls />}></Route>
              <Route path = "/-/register" element = {<Register />}></Route>
        
              {/* <Route path = "/" element = {<AddUrlComponent />}></Route>
              <Route path = "/shorten-url" element = {<AddUrlComponent />}></Route> */}
              {/* <Route path = "/React" element = {<ShortUrlComponent />}></Route> */}
            </Routes>
          </div>
        <FooterComponent />
      </Router>
    </div>


  );
}

export default App;
 