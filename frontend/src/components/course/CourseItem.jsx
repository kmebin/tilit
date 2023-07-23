import { useState } from 'react';
import { Button, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const CourseItem = ({ id, category, name, teacherId, teacherName, price, onClickAddToCart, onClickDelete }) => {
  const [isAdded, setIsAdded] = useState(false);
  const userId = localStorage.getItem('id');

  const handleAddToCart = (e) => {
    e.preventDefault();
    setIsAdded(true);
    onClickAddToCart(id);
  };

  const handleDelete = (e) => {
    e.preventDefault();
    onClickDelete(id);
  };

  return (
    <Link to={`/courses/${id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
      <Card className='mb-3' style={{ width: '100%' }}>
        <Card.Header>{category}</Card.Header>
        <Card.Body>
          <Card.Title>{name}</Card.Title>
          <Card.Text>{teacherName}</Card.Text>
          <Card.Text> ₩{price.toLocaleString('ko-KR')}</Card.Text>
          {userId && Number(userId) === teacherId ? (
            <div className='d-flex'>
              <Link to={`/courses/${id}/update`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <Button className='me-2'>수정</Button>
              </Link>
              <Button variant='danger' onClick={handleDelete}>
                삭제
              </Button>
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
