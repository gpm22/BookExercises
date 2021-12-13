import logo from './logo.svg';
import './App.css';
import Header from './commons/Header';
import Footer from './commons/Footer';

function App() {
  return (
    <div className="App">
      <Header/>
      <main className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </main>
      <Footer/>
    </div>
  );
}

export default App;
