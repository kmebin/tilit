import { Button, Card } from 'react-bootstrap';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const CourseItem = ({ id, category, name, teacherId, teacherName, price, onClickAddToCart, user }) => {
  const [isAdded, setIsAdded] = useState(false);

  const handleAddToCart = (e) => {
    e.preventDefault();
    setIsAdded(true);
    onClickAddToCart(id);
  };

  return (
    <Link to={`/courses/${id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
      <Card className='mb-3' style={{ width: '100%' }}>
        <Card.Header>{category}</Card.Header>
        <Card.Body>
          <Card.Title>{name}</Card.Title>
          <Card.Text>{teacherName}</Card.Text>
          <Card.Text> ₩{price.toLocaleString('ko-KR')}</Card.Text>
          {user && user.id === teacherId ? (
            <div className='d-flex'>
              <Link to={`/course/create`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <Button className='me-2'>수정</Button>
              </Link>
              <Button variant='danger'>삭제</Button>
            </div>
          ) : (
            <Button variant='outline-primary' disabled={isAdded} onClick={handleAddToCart}>
              담기
            </Button>
          )}
        </Card.Body>
      </Card>
    </Link>
  );
};

export default CourseItem;
