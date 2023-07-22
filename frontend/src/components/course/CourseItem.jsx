import { Button, Card } from 'react-bootstrap';
import { useState } from 'react';

const CourseItem = ({ id, category, name, teacher, price, onClickAddToCart }) => {
  const [isAdded, setIsAdded] = useState(false);

  const handleAddToCart = () => {
    setIsAdded(true);
    onClickAddToCart(id);
  };

  return (
    <Card className='mb-3' style={{ width: '100%' }}>
      <Card.Header>{category}</Card.Header>
      <Card.Body>
        <Card.Title>{name}</Card.Title>
        <Card.Text>{teacher}</Card.Text>
        <Card.Text> ₩{price.toLocaleString('ko-KR')}</Card.Text>
        <Button variant='outline-primary' disabled={isAdded} onClick={handleAddToCart}>
          담기
        </Button>
      </Card.Body>
    </Card>
  );
};

export default CourseItem;
