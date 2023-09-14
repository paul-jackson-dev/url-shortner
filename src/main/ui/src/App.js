import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom' //Switch renders only one component at a time
import ShortUrlComponent from './components/ShortUrlComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

function App() { // App.js is the root component for React
  return (
    <div>
      <Router>
        <HeaderComponent />
          <div className="container">
            <Routes>
              <Route path = "/" element = {<ShortUrlComponent />}></Route>
              <Route path = "/React" element = {<ShortUrlComponent />}></Route>
              {/* <ShortUrlComponent /> */}
            </Routes>
          </div>
        <FooterComponent />
      </Router>
    </div>


  );
}

export default App;
 