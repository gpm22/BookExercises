import logo from './logo.svg';
import './App.css';

function App(props) {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          <b><em>Hello, {props.subject}!</em></b>
        </p>
      </header>
    </div>
  );
}

export default App;
