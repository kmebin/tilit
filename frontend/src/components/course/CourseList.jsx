import { Container, Row, Col } from 'react-bootstrap';
import CourseItem from './CourseItem';

const CourseList = ({ courses, onClickAddToCart }) => {
  return (
    <Container>
      <h5>
        <b>전체 강의</b>
      </h5>
      <hr />
      <Row>
        {courses.map((course) => (
          <Col key={course.id}>
            <CourseItem {...course} onClickAddToCart={onClickAddToCart} />
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default CourseList;
