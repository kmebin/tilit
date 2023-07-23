import { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const CreateCourseForm = ({ onClickCreate }) => {
  const [validated, setValidated] = useState(false);
  const [form, setForm] = useState({ category: 'APP', name: '', description: '', price: 0 });
  const { category, name, description, price } = form;

  const handleSubmit = (e) => {
    e.preventDefault();

    if (e.currentTarget.checkValidity() === false) {
      e.stopPropagation();
    } else {
      onClickCreate(form);
    }
    setValidated(true);
  };

  const handleCategoryChange = (e) => {
    setForm({ ...form, category: e.target.value });
  };

  const handleNameChange = (e) => {
    setForm({ ...form, name: e.target.value });
    const length = e.target.length;
    setValidated(length > 0 && length <= 100);
  };

  const handleDescriptionChange = (e) => {
    setForm({ ...form, description: e.target.value });
    const length = e.target.length;
    setValidated(length > 0);
  };

  const handlePriceChange = (e) => {
    setForm({ ...form, price: e.target.value });
    setValidated(e.target.value > 0);
  };

  return (
    <Container style={{ maxWidth: '560px' }}>
      <h4 className='mb-3 mt-5'>회원가입</h4>
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Form.Select value={category} onChange={handleCategoryChange}>
          <option value='WEB'>웹</option>
          <option value='APP'>앱</option>
          <option value='GAME'>게임</option>
          <option value='ALGORITHM'>알고리즘</option>
          <option value='DATABASE'>데이터베이스</option>
          <option value='INFRA'>인프라</option>
        </Form.Select>

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
                강의 생성
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

export default CreateCourseForm;
