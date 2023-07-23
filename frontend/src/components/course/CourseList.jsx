import { Row, Col } from 'react-bootstrap';
import CourseItem from './CourseItem';

const CourseList = ({ courses, onClickAddToCart, user }) => {
  return (
    <Row xs={1} sm={2} md={3}>
      {courses.map((course) => (
        <Col key={course.id} className='mb-3'>
          <CourseItem {...course} onClickAddToCart={onClickAddToCart} user={user} />
        </Col>
      ))}
    </Row>
  );
};

export default CourseList;
