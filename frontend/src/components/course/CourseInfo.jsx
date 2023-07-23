import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { getCourseDetail } from '../../apis/course';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

const CourseInfo = ({ onClickRegister, onClickDelete }) => {
  const userId = localStorage.getItem('id');
  const { courseId } = useParams();
  const [courseDetail, setCourseDetail] = useState({});
  const { id, category, name, teacherId, teacherName, description, price, studentCount } = courseDetail;

  const handleRegister = () => {
    onClickRegister(id);
  };

  const handleDelete = () => {
    onClickDelete(id);
  };

  useEffect(() => {
    const fetchData = async () => {
      const res = await getCourseDetail(courseId);
      if (res.status === 200) {
        setCourseDetail(res.data);
      } else {
        alert(res.message);
      }
    };
    fetchData();
  }, [courseId]);

  return (
    <Card>
      <Row>
        <h5>
          <b>강의 정보</b>
        </h5>
        <hr />
      </Row>
      <Row>
        <Col md={8} className='mt-4 d-flex flex-column align-items-start p-3 pt-0'>
          <Container>
            <Card>
              <Row>
                <Card>
                  <Card.Header>{category}</Card.Header>
                  <Card.Body>
                    <Card.Title>{name}</Card.Title>
                    <Card.Text>
                      강사: <strong>{teacherName}</strong>
                    </Card.Text>
                    <Card.Text>
                      <span className='text-primary'>{studentCount}</span>명의 수강생
                    </Card.Text>
                  </Card.Body>
                  <Card.Text>소개</Card.Text>
                  <Card body>{description}</Card>
                </Card>
              </Row>
            </Card>
          </Container>
        </Col>
        <Col md={4} className='summary p-4'>
          <Container>
            <Row className='pt-2 pb-3'>
              <Col>
                <h5>{price ? price.toLocaleString('ko-KR') : price}원</h5>
              </Col>
            </Row>
            {userId && Number(userId) === teacherId ? (
              <Row className='mt-3'>
                <Col>
                  <Link to={`/courses/${id}/update`} style={{ textDecoration: 'none', color: 'inherit' }}>
                    <div className='d-grid'>
                      <Button variant='primary'>수정하기</Button>
                    </div>
                  </Link>
                </Col>
                <Col>
                  <div className='d-grid'>
                    <Button variant='danger' onClick={handleDelete}>
                      삭제하기
                    </Button>
                  </div>
                </Col>
              </Row>
            ) : (
              <div className='d-grid gap-2'>
                <Button onClick={handleRegister}>수강신청하기</Button>
              </div>
            )}
          </Container>
        </Col>
      </Row>
    </Card>
  );
};

export default CourseInfo;
