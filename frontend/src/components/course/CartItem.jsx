import { Card } from 'react-bootstrap';

const CartItem = (props) => {
  const { name, price } = props;

  return (
    <Card style={{ margin: '10px' }}>
      <Card.Body style={{ display: 'flex', justifyContent: 'space-between' }}>
        <Card.Text style={{ margin: 0 }}>{name}</Card.Text>
        <Card.Text style={{ margin: 0 }}>{price.toLocaleString('ko-KR')}원</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default CartItem;
