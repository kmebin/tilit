import { Button, Card } from 'react-bootstrap';
import { useState } from 'react';

const CourseItem = (props) => {
  const { id, category, name, teacher, price, onClickAddToCart } = props;
  const [isAddedToCart, setIsAddedToCart] = useState(false);

  const handleAddToCart = () => {
    setIsAddedToCart(true);
    onClickAddToCart(id);
  };

  return (
    <Card className='mb-2' style={{ width: '18rem' }}>
      <Card.Header>{category}</Card.Header>
      <Card.Body>
        <Card.Title>{name}</Card.Title>
        <Card.Text>{teacher}</Card.Text>
        <Card.Text> ₩{price.toLocaleString('ko-KR')}</Card.Text>
        <Button variant='primary' disabled={isAddedToCart} onClick={handleAddToCart}>
          담기
        </Button>
      </Card.Body>
    </Card>
  );
};

export default CourseItem;
