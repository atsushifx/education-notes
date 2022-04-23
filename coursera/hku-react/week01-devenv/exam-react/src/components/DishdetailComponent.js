import React, { Component } from'react';

// System Component
import { Media } from 'reactstrap';
import { Card, CardImg, CardImgOverlay, CardTitle, CardBody, CardText } from 'reactstrap';


/**
 * DishDetail
 *   選んだメニューの詳細を表示する
 */
class DishDetail extends Component {
  // コンストラクタ
  constructor(props) {
    super(props);
  }

  // render
  /**
   * renderDish
   *   選択したメニューの詳細を表示
   * 
   * @returns jsx for selected dish
   */
  renderDish(dish) {
    if (dish==null) {
      return (
        <div>

        </div>
      );
    }

    return (
      <Card>
        <CardImg top width='100%' src={dish.image} alt='{dish.name}' />
        <CardBody>
          <CardTitle>{dish.name}</CardTitle>
            <CardText>
              <p>{dish.description}</p>
              <p className='text-right'>${dish.price}</p>
            </CardText>
          </CardBody>
      </Card>
    );
  }

  /**
   * renderComments
   * 
   * @returns jsx for comments
   */
  renderComments(dish) {
    if (dish == null) {
      return (
        <div>

        </div>
      );
    }

    console.log('comments:', dish.comments);
    const commenter = dish.comments.map((c) => {
      return (
        <Media tag='li'>
          { c.rating } <br />
          { c.comment } <br />
          -- { c.author } ,  { c.date }
        </Media>
      );
    });
    
    return (
      <Media list className='dishdetail-comments' >
        { commenter }
      </Media>
    );
  }


  /**
   * render
   *   メニュー詳細のjsxを作成
   */ 
  render() {
    return (
      <div className='row'>
       <div className='col-6 col-md-5 col-sm-12 m-1'>
          { this.renderDish(this.props.dish)}
        </div>
        <div className='col-6 col-md-5 col-sm-12 m-1'>
          { this.renderComments(this.props.dish) }
        </div>
      </div>
    );
  }
}

export { DishDetail };
