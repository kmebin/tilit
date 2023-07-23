import { useState, useEffect } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router-dom';
import { getCourseDetail } from '../../apis/course';

const UpdateCourseForm = ({ onClickUpdate }) => {
  const { courseId } = useParams();
  const [validated, setValidated] = useState(false);
  const [form, setForm] = useState({ name: '', description: '', price: 0 });
  const { name, description, price } = form;

  useEffect(() => {
    const fetchData = async () => {
      const res = await getCourseDetail(courseId);
      if (res.status === 200) {
        setForm(res.data);
      } else {
        alert(res.message);
      }
    };
    fetchData();
  }, [courseId]);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (e.currentTarget.checkValidity() === false) {
      e.stopPropagation();
    } else {
      onClickUpdate(form);
    }
    setValidated(true);
  };

  const handleNameChange = (e) => {
    setForm({ ...form, name: e.target.value });
    const isValid = e.target.checkValidity();
    setValidated(isValid);
  };

  const handleDescriptionChange = (e) => {
    setForm({ ...form, description: e.target.value });
    const isValid = e.target.checkValidity();
    setValidated(isValid);
  };

  const handlePriceChange = (e) => {
    setForm({ ...form, price: e.target.value });
    const isValid = e.target.checkValidity();
    setValidated(isValid);
  };

  return (
    <Container style={{ maxWidth: '560px' }}>
      <h4 className='mb-3 mt-5'>강의 수정</h4>
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Form.Group controlId='name' className='mb-3'>
          <Form.Label>강의명</Form.Label>
          <Form.Control
            type='text'
            placeholder='강의명'
            required
            value={name}
            onChange={handleNameChange}
            isInvalid={name.length === 0 || name.length > 100}
          />
          <Form.Control.Feedback type='invalid'>강의명은 최대 100자까지 가능합니다.</Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId='description' className='mb-3'>
          <Form.Label>강의 소개</Form.Label>
          <Form.Control
            as='textarea'
            rows={3}
            placeholder='강의 소개'
            required
            value={description}
            onChange={handleDescriptionChange}
            isInvalid={description.length === 0}
          />
          <Form.Control.Feedback type='invalid'>강의 소개는 필수입니다.</Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId='price' className='mb-3'>
          <Form.Label>강의 가격</Form.Label>
          <Form.Control type='number' required value={price} onChange={handlePriceChange} isInvalid={price < 0} />
          <Form.Control.Feedback type='invalid'>강의 가격은 0원 이상이어야 합니다.</Form.Control.Feedback>
        </Form.Group>

        <hr className='my-4' />

        <Row className='mt-3'>
          <Col>
            <div className='d-grid'>
              <Button variant='primary' size='lg' type='submit' disabled={!validated}>
                강의 수정
              </Button>
            </div>
          </Col>
          <Col>
            <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
              <div className='d-grid'>
                <Button variant='secondary' size='lg'>
                  취소
                </Button>
              </div>
            </Link>
          </Col>
        </Row>
      </Form>
    </Container>
  );
};

export default UpdateCourseForm;
