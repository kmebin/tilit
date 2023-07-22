import { Container, Row, Col, Card, Button } from 'react-bootstrap';

const CourseInfo = ({ id, category, name, teacher, description, price, studentCount, onClickRegister }) => {
  const handleRegister = () => {
    onClickRegister(id);
  };

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
                    <Card.Text>강사: {teacher}</Card.Text>
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
                <h5>{price.toLocaleString('ko-KR')}원</h5>
              </Col>
            </Row>
            <div className='d-grid gap-2'>
              <Button onClick={handleRegister}>수강신청하기</Button>
            </div>
          </Container>
        </Col>
      </Row>
    </Card>
  );
};

export default CourseInfo;
