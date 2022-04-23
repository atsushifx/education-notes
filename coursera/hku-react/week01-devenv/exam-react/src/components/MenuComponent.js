import React, { Component } from'react';

// System Component
import { Card, CardImg, CardImgOverlay, CardTitle, CardBody, CardText } from 'reactstrap';

// user component
import { DishDetail } from './DishdetailComponent';

/**
 * MenuComponent
 *   レストランのメニュー(写真付き)
 * 
 */
class Menu extends Component {
     /**
     * コンストラクタ
     */
    constructor(props) {
        super(props);

        this.state = {
          selectedDish: null,
        }
    }

  // event handler
  /**
   * onDishSelect
   *   メニューを選んだときの処理
   * 
   */
  onDishSelect(dish) {
    this.setState({
      selectedDish: dish
    });
  }

  // jsx render
  /**
   * main render
   * 
   */
  render() {
    /**
      * メニュー作成
      */
    const menu = this.props.dishes.map((dish) => {
      return (
        <div className="col-12 col-md-5 m-1">
          <Card key={dish.id} onClick = {() => this.onDishSelect(dish)}>
            <CardImg width="100%" src={dish.image} alt={dish.name} />
            <CardImgOverlay>
              <CardTitle>{dish.name}</CardTitle>
            </CardImgOverlay>
          </Card>
        </div>
      );
    });
    
        
    return (
      <div className='container'>
        <div className="row">
          {menu}
        </div>
        <DishDetail dish={this.state.selectedDish} />
      </div>
    );
  }
}

export default Menu;
