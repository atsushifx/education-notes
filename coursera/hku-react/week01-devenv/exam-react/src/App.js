import { Component } from 'react';

// System components
import { Navbar, NavbarBrand } from 'reactstrap';

// user components
import Menu from './components/MenuComponent';

// model
import { DISHES } from './shared/dishes';

// stylesheet
import './App.css';


/**
 * main roution 
 *
 */
class App extends Component {
  // コンストラクタ
  constructor(props) {
      super(props);

      this.state = {
        dishes : DISHES
      }
  }

  // 
  render() {
    return (
      <div className="App">
        <Navbar dark color="primary">
          <div className='container'>
            <NavbarBrand href='/'>Ristrante First React</NavbarBrand>
          </div>
        </Navbar>
        <Menu dishes={this.state.dishes} />
      </div>
    );
  }
}

export default App;
