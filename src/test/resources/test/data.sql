INSERT INTO CATEGORY (name, category_type, depth_level, is_refundable, status, status_update_time)
VALUEs (
           '패션', 'CLOTH', 'DEPTH_ONE', true, 2001, Now()
       );

INSERT INTO CATEGORY (name, category_type, depth_level, is_refundable, status, status_update_time)
VALUEs (
           '패션2', 'CLOTH', 'DEPTH_ONE', true, 2001, Now()
       );

/* 상품 */
-- 남성 패션
INSERT INTO ITEM (name, description, brand_name, price,
                  package_type, origin,
                  model_serial_no, search_keyword,
                  stock, is_oneday_eligible, category_id)
VALUES (
           '어반티 1+1+1 드라이 라운드 쿨티셔츠 3장세트 남여공용 기능성 반팔티 냉감 반팔 티셔츠',
           '극강의 시원함, 최강의 가성비', '어반티', 14900,
           '비닐포장', '국내산',
           '1835265762 - 3121080524', '가성비 티셔츠',
           365, true, 1
       );

-- 뷰티
INSERT INTO ITEM (name, description, brand_name,
                  price, volume,
                  package_type, origin, expiration, model_serial_no, search_keyword, stock, is_oneday_eligible, category_id)
VALUES (
           '뉴트로지나 딥클린 포밍 클렌저', '이유있는 1등 클렌저, 뉴트로지나 딥쿨린 데일리 포밍 클렌저', '뉴트로지나',
           11930, '100g', '종이박스', '태국/한국', '제조일로부터 2년', '4932903828 - 103419341',
           '노폐물 제거', 213, true, 1
       );

-- 출산/유아동
INSERT INTO ITEM (name, description, brand_name, price, volume, package_type, origin, expiration, price_per_100g,
                  allergic_info, model_serial_no, search_keyword, stock, is_oneday_eligible, is_fresh_eligible, category_id)
VALUES (
           '앱솔루트 프리미엄 명작 분유 2단계', '내 아이를 위한 고급 분유', '매일유업', 53000, '800g',
           '종이박스', '국내산', '제조일로부터 2년', 2208, '연유 함유', '7244871 - 32023703', '인기 분유'
           , 321, true, true, 1
       );

-- 식품
INSERT INTO ITEM (name, description, brand_name, price, volume, package_type, origin, expiration, price_per_100g,
                  allergic_info, model_serial_no, stock, is_oneday_eligible, is_fresh_eligible, category_id)
VALUES (
           '목우촌 국내산 소고기 채끝 구이용 (냉장)', '우리가 직접 확인한 양질의 소고기', '목우촌', 17850, '300g',
           '플라스틱', '국내산', '제조일로부터 2주일', 5950, '소고기', '1290671052 - 2301717160', 21, true, true, 1
       );

-- 주방용품
INSERT INTO ITEM (name, description, brand_name, price, package_type, origin, model_serial_no, stock, is_oneday_eligible, category_id)
VALUES (
           '한샘 올스텐 핸들 주방칼 3종', '날카롭고 튼튼한 일체형 식칼 3종 세트', '한샘', 25900, '종이', '국내산',
           '291405252 - 921804644', 91, true, 1
       );

-- 생활용품
INSERT INTO ITEM (name, description, brand_name, price, package_type, origin, allergic_info, model_serial_no, stock, category_id)
VALUES (
           '질레트 프로쉴드 파워 옐로우 면도기 + 여분날 3p 세트', '초당 100번의 미세진동으로 면도를 더 밀착되게!', '질레트', 29000,
           '종이', '수입산', '금속 함유', '1741089034 - 2964203269', 213, 1
       );

-- 홈 인테리어
INSERT INTO ITEM (name, description, brand_name, price, dimension, model_serial_no, stock, is_oneday_eligible, category_id)
VALUES (
           '코멧 모던스타일 테이블', '목제 상판에 알루미늄 받침대', '코멧', 19890, '790x590(mm)', '2354065065 - 4084109222', 22, true, 1
       );

-- 가전 디지털
INSERT INTO ITEM (name, description, brand_name, price, dimension, package_type, origin, model_serial_no, search_keyword, stock, is_oneday_eligible, category_id)
VALUES (
           '직수입, 샤오미 미에어 공기청정기 3H 필터', '호환모델: 2H, 2S, Pro, 3H', '샤오미', 29800, '210x310(mm)', '비닐', '중국', '332378484 - 1061903125',
           '샤오미 공기청정기', 231, true, 1
       );

-- 스포츠/레저
INSERT INTO ITEM (name, description, brand_name, price, dimension, package_type, origin, model_serial_no, search_keyword, stock, is_oneday_eligible, category_id)
VALUES (
           '금양 파워타켓 450 원투낚시대 원투대', '보급형 SIC 낚시대', '금양',
           29240, '표준전장(4.50m), 접은길이(121cm)', '카드보드 박스', '국내산', '254982055 - 800318477', '바다낚시', 231, true, 1
       );

-- 자동차용품
INSERT INTO ITEM (name, description, brand_name, price, package_type, origin, model_serial_no, search_keyword, stock, category_id)
VALUES (
           '코시 QC3.0 USB 2포트 차량용 자동감김 급속 충전기 타입C', '최대 3배 빠른 QC3.0포트 지원',
           '코시', 16300, '폴리에스테르', '국내산', 'CGR3247AT', '차량용 충전기', 21, 1
       );

-- 도서
INSERT INTO ITEM (name, description, price, package_type, search_keyword, stock, author, publisher, isbn, published_time, is_oneday_eligible, category_id)
VALUES (
           '마법천자문. 51: 찬란하게 빛나라! 빛날 휘', '51권을 기대해 주세요!', 10800, '폴리에스테르', '교육용 만화책', 222, '홍거북(그림), 유대영(글)', '아울북',
           '978-89-509-9590-4', '2021-07-12', true, 1
       );

-- 문구
INSERT INTO ITEM (name, description, brand_name, price, package_type, stock, origin, is_oneday_eligible, category_id)
VALUES (
           'LAMY T10 만년필용 일회용 잉크 카트리지 5p', '호환 모델: T10', 'LAMY', 10310, '종이', 99, '독일', true, 1
       );

-- 반려동물
INSERT INTO ITEM (name, description, brand_name, price, volume, package_type, origin, expiration, search_keyword, stock, category_id)
VALUES (
           '퓨리나 프리스키 파티믹스 고양이 수제 간식', '다양한 맛이 믹스된 영양간식!', '퓨리나', 19600, '5개', '비닐', '수입산',
           '제조일로부터 1년', '고양이 수제간식', 234, 1
       );

-- 헬스/건강식품
INSERT INTO ITEM (name, description, brand_name, price, volume, package_type, origin, expiration,
                  allergic_info, search_keyword, stock, is_oneday_eligible, category_id)
VALUES (
           '마이밀 마시는 뉴프로틴', '고함량 균형단백질', '마이밀', 14580, '190ml', '카드보드 박스', '국내산', '제조일로부터 3개월',
           '대두 함유', '건강 보조 식품', 23, true, 1
       );