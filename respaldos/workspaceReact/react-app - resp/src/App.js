import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import TableroVivify from './components/tableroVivify'
/*import Tablero from './components/tablero'
import Responsible from './components/tablero/Responsible'
import Members from './components/tablero/Members'
*/
class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      url: "",
      data: null
    }
  }
  componentWillMount() {
  }
  render() {
    return (
      <div className="App">
        <img src={logo} className="App-logo" width="50" height="50" alt="logo" />
        <hr/>
        <TableroVivify />
        {/*
        <hr/>
        <Tablero />
        <br/>
        <div align="center">
          <Responsible />
          <br />
          <br />
          <Members />
        </div>
        <br />
        <br />*/}
        
      </div>
    );
  }
}

export default App;
