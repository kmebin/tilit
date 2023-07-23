import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { getCourseDetail } from '../../apis/course';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

const CourseInfo = ({ onClickRegister, onClickDelete }) => {
  const userId = localStorage.getItem('id');
  const { courseId } = useParams();
  const [courseDetail, setCourseDetail] = useState({
    id: 0,
    category: '',
    name: '',
    teacherId: 0,
    teacherName: '',
    description: '',
    price: 0,
    studentCount: 0,
  });
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
    <Container style={{ maxWidth: '560px' }}>
      <h4 className='mb-3 mt-5'>강의 정보</h4>
      <Form>
        <Form.Group controlId='category' className='mb-3'>
          <Form.Label>카테고리</Form.Label>
          <Form.Control type='text' value={category} readOnly />
        </Form.Group>

        <Form.Group controlId='name' className='mb-3'>
          <Form.Label>강의명</Form.Label>
          <Form.Control type='text' value={name} readOnly />
        </Form.Group>

        <Form.Group controlId='teacher' className='mb-3'>
          <Form.Label>강사</Form.Label>
          <Form.Control type='text' value={teacherName} readOnly />
        </Form.Group>

        <Form.Group controlId='description' className='mb-3'>
          <Form.Label>소개</Form.Label>
          <Form.Control as='textarea' rows={3} value={description} readOnly />
        </Form.Group>

        <Form.Group controlId='price' className='mb-3'>
          <Form.Label>가격</Form.Label>
          <Form.Control type='text' value={price.toLocaleString('ko-KR') + '원'} readOnly />
        </Form.Group>

        <Form.Group controlId='price' className='mb-3'>
          <Form.Label>수강생</Form.Label>
          <Form.Control type='text' value={studentCount.toLocaleString('ko-KR') + '명'} readOnly />
        </Form.Group>

        <hr className='my-4' />
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
      </Form>
    </Container>
  );
};

export default CourseInfo;
