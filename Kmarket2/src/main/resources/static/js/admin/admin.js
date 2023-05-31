/*
	날짜 : 2023/02/17 
	이름 : 김지홍
	내용 : admin 의 자바스크립트 함수 모음
*/

document.addEventListener("DOMContentLoaded", ()=>{
	const currentUrl = window.location.href;
	asideSlide(currentUrl);
});
// admin/register 1차 분류 선택시 2차 분류 가져오기
function cateList(){
	let admin_cate1 = document.getElementById("admin_cate1");
    let admin_cate2 = document.getElementById("admin_cate2");
    admin_cate1.addEventListener("change", ()=>{
		let cate1 = admin_cate1.value;
		let tag = `<option value="" selected>2차 분류 선택</option>`;
		fetch("/kmarket2/cate1/" + cate1)
			.then(res => res.json())
    		.then((data) => {
				console.log(data);
      			admin_cate2.innerHTML = "";
				for (const e of data) {
		        	tag +=`<option value="${e.cate2}">${e.c2Name}</option>`;
      			}
      			admin_cate2.insertAdjacentHTML('beforeend', tag);
      
			});
	});
}
// admin/register point 계산(price 의 10%)
function calcPoint(){
	let admin_price = document.getElementById("admin_price");
	let admin_point = document.getElementById("admin_point");
	admin_price.addEventListener("focusout", ()=>{
		admin_point.value = Math.floor(Number(admin_price.value) * 0.1);	
	});
}
// admin 공통 aside 슬라이드
function asideSlide(currentUrl){
    let gnb = $('#gnb > li > a');

    gnb.click(function (e) {
      e.preventDefault();
      let isOpen = $(this).next().is(':visible');

      if (isOpen) {
        $(this).next().slideUp(200);
      } else {
        $(this).next().slideDown(200);
      }
    });
    
    // 해당 주소 접속시 슬라이드 펼쳐놓기
     
	if(currentUrl.includes('product')){
		$('#gnb > li:nth-child(4) > a').next().css("display", "block");
	}else if(currentUrl.includes('cs')){
		$('#gnb > li:nth-child(7) > a').next().css("display", "block");
	}
    
}
// admin product list 검색 기능
let totalPosts = 0;

async function updateTable(page) {
	let admin_condition = document.getElementById('admin_condition');
	let search_value = document.getElementById('search_value');
	//let btn_admin_search  = document.getElementById('btn_admin_search');
	let table  = document.getElementById('admin_table');
	const response = await fetch(
		'/kmarket2/admin/search?' 
		+ admin_condition.value 
		+ '=' 
		+ search_value.value 
		+ '&pageNum=' 
		+ page, {async: false});
	const data = await response.json();
	totalPosts = data.totalElements;
	table.tBodies[0].innerHTML = '';
	data.content.forEach(product => {
    const row = document.createElement('tr');
    row.innerHTML = 
    `<td><input type="checkbox" name="" id="" class="form-check-input"></td>
        <td class="admin_list_img"><img src="/kmarket2/images/${product.thumb1}" alt="" class="img-fluid"></td>
        <td>${product.prodNo}</td>
        <td>${product.prodName}</td>
		<td>${product.price}</td>
		<td>${product.discount}</td>
		<td>${product.point}</td>
		<td>${product.stock}</td>
		<td>${product.seller}</td>
		<td>${product.hit}</td>
        <td>
          <button class="btn btn-info">수정</button>
          <button class="btn btn-secondary btnDeleteProd" th:id="${product.prodNo}">삭제</button>
        </td>`;
        table.tBodies[0].appendChild(row);
  	});
  	updatePagination(page);
}

// admin product pagenation 업데이트
function updatePagination(currentPage) {
  const pageSize = 10;
  const totalPages = Math.ceil(totalPosts / pageSize);
  const pagesPerGroup = 10;
	
  const pagination = document.getElementById('paging_nav');
  let pageGroup = Math.floor((currentPage - 1) / pagesPerGroup) + 1;
  let startPage = (pageGroup - 1) * pagesPerGroup + 1;
  let endPage = Math.min(startPage + pagesPerGroup - 1, totalPages);

  let paginationHtml = '<nav><ul class="pagination justify-content-center">';

  if (startPage > 1) {
    paginationHtml += `
    <li>
    	<a href="javascript:void(0);" onclick="updateTable(${startPage - 1})" aria-label="Previous" class="page-link">
    		<span aria-hidden="true">&laquo;</span>
    	</a>
	</li>`;
  }

  for (let i = startPage; i <= endPage; i++) {

    paginationHtml += `
    <li class="page-item">
    	<a href="javascript:void(0);" onclick="updateTable(${i})" class="page-link curPage">
    		${i}
		</a>
	</li>`;
  }

  if (endPage < totalPages) {
    paginationHtml += `
    <li class="page-item">
    	<a href="javascript:void(0);" onclick="updateTable(${endPage + 1})" aria-label="Next" class="page-link">
    		<span aria-hidden="true">&raquo;</span>
		</a>
	</li>`;
  }

  paginationHtml += '</ul></nav>';

  pagination.innerHTML = paginationHtml;
  let curPage = document.getElementsByClassName('curPage');
  currentPage = currentPage > 10 ? currentPage - 10 : currentPage;
  curPage[currentPage-1].classList.add('active');
}
// admin product list 검색 이벤트
function adminListSeachEvent(){
	btn_admin_search.addEventListener('click', function(e){
		updateTable(1);
	});
	search_value.addEventListener('keyup', function(e){
		if(e.key === 'Enter'){
			updateTable(1);
		}
	});
}

// admin notice 삭제
function adminDeleteNotice(){
	let deleteList = document.getElementsByClassName('btnDeleteNotice');
	for(let i=0; i < deleteList.length; i++){
		deleteList[i].addEventListener('click', function(e){
			if(!confirm('삭제하시겠습니까?')){
				e.preventDefault();
				return false;
			}
			let id = e.target.id;
			e.preventDefault();
			fetch('/kmarket2/admin/cs/notice/delete?no='+id,{method:"DELETE",})
			.then(res =>{
				if(res.ok){
					alert('삭제 성공');
					location.href = '/kmarket2/admin/cs/notice/list';
				}else{
					alert('삭제 실패');
				}		
			}) 
		});
	}
}



// adimin cs notice cate1 이 바뀔때 테이블 업데이트
function getAdminCate1Notice(){
	let cate1 = document.getElementById('admin_cs_cate1');
	cate1.addEventListener('change', function(e){
		location.href = '/kmarket2/admin/cs/notice/list?cate1='+cate1.value;
	});
}
// adimin cs faq cate1 이 바뀔때 테이블 업데이트
function getAdminCate1Faq(){
	let cate1 = document.getElementById('admin_cs_cate1');
	cate1.addEventListener('change', function(e){
		location.href = '/kmarket2/admin/cs/faq/list?cate1='+cate1.value;
	});
}
// adimin cs faq cate1 이 바뀔때 테이블 업데이트
function getAdminCate1Qna(){
	let cate1 = document.getElementById('admin_cs_cate1');
	cate1.addEventListener('change', function(e){
		location.href = '/kmarket2/admin/cs/qna/list?cate1='+cate1.value;
	});
}
// cate 값이 존재할때 select 값 설정
function setAdminCate(){
	let urlSearch = new URLSearchParams(location.search);
	//let cate1 = document.getElementById('admin_cs_cate1');
	let cate2 = document.getElementById('admin_cs_cate2');
	//let cate1Value = urlSearch.get('cate1');
	let cate2Value = urlSearch.get('cate2');
	/*if(cate1 != null && cate1Value != null){
		cate1.value = cate1Value;
	}*/
	if(cate2 != null && cate2Value != null){
		cate2.value = cate2Value;
	}
}
// cate1 값이 변화할때 cate2 select 불러오기
function setAdminCate2Select(){
	let cate1 = document.getElementById('admin_cs_cate1');
	let cate2 = document.getElementById('admin_cs_cate2');
	cate1.addEventListener('change', function(e){
		if(cate1.value == 0){
			location.href = `/kmarket2/admin/cs/qna/list?cate1=0&cate2=0`;
		}
		else if(cate1.value != 0 && cate1 != null){
			let url = '/kmarket2/admin/cs/cate2/'+cate1.value;
			fetch(url).then(res=>res.json()).then(data=>{
				//console.log(data);
				cate2.innerHTML = '';
				const optionDefalut = document.createElement('option');
				optionDefalut.value = '0'; 
				optionDefalut.textContent = '2차 유형';
				cate2.appendChild(optionDefalut);
				data.forEach(ele =>{
					const option = document.createElement('option');
					option.value = ele.cate2;
					option.textContent = ele.c2Name;
					cate2.appendChild(option);
				})
			})		
		}	
	})	
}
// 묻고답하기 cate2 바뀔때 테이블 업데이트
function getAdminCate2Qna(){
	let cate1 = document.getElementById('admin_cs_cate1');
	let cate2 = document.getElementById('admin_cs_cate2');
	cate2.addEventListener('change', function(e){
		location.href = `/kmarket2/admin/cs/qna/list?cate1=${cate1.value}&cate2=${cate2.value}`;	
	})
}
// 묻고답하기 삭제
function adminDeleteQna(){
	
	let deleteList = document.getElementsByClassName('btnDeleteQna');
	deleteList[0].addEventListener('click', function(e){
		if(!confirm('삭제하시겠습니까?')){
			e.preventDefault();
			return false;
		} 
		let id = e.target.id;
		e.preventDefault();
		fetch('/kmarket2/admin/cs/qna/delete?no='+id,{method:"DELETE",})
		.then(res =>{
			if(res.ok){
				alert('삭제 성공');
				location.href = '/kmarket2/admin/cs/qna/list';
			}else{
				alert('삭제 실패');
			}		
		}) 
	});
}
// 관리자 상품 삭제
function adminDeleteProd(){
	
	let deleteList = document.getElementsByClassName('btnDeleteProd');
		for(let i = 0; i < deleteList.length; i++){
			deleteList[i].addEventListener('click', function(e){
			if(!confirm('삭제하시겠습니까?')){
				e.preventDefault();
				return false;
			} 
			let id = e.target.id;
			e.preventDefault();
			fetch('/kmarket2/admin/product/delete?prodNo='+id,{method:"DELETE",})
			.then(res =>{
				if(res.ok){
					alert('삭제 성공');
					location.href = '/kmarket2/admin/product/list';
				}else{
					alert('삭제 실패');
				}		
			}) 
		});	
	}
}
function adminDeleteProdEvent(){
	let table = document.getElementById('admin_table');
	table.addEventListener('click', function(e){
		alert('tableEvent');
	});
}
// 문의내역 - 상태 변경(0 = 검토중, 1 = 답변완료) 
function qnaStat(){
    let answer = document.getElementsByClassName('qna_stat');
    for (let i = 0; i < answer.length; i++) {
        if(answer[i].textContent === '0'){
            answer[i].textContent = '검토중';
            answer[i].style.color = 'gray';
        }else{
            answer[i].textContent = '답변완료';
            answer[i].style.color = 'green';
        }
    }
}
// 모두 체크 설정
function checkAll(){
	let checkAll = document.getElementById('check_all');
	checkAll.addEventListener('change', function(e){
		let check = document.getElementsByClassName('check_no');
		for(let i=0; i<check.length; i++){
			if(check[i].checked == false && checkAll.checked == true){
				check[i].checked = true;	
			}else if(check[i].checked == true && checkAll.checked == false){
				check[i].checked = false;
			}
		}
	});
}

// 선택삭제
function deleteCheck(deleteurl, outUrl){
	let btn_check_delete = document.getElementById('btn_check_delete');
	btn_check_delete.addEventListener('click', function(e){
		let deleteList = [];
		let checkList = document.getElementsByClassName('check_no');
		for(let i=0; i<checkList.length; i++){
			if(checkList[i].checked == true){
				deleteList.push(checkList[i].dataset.no);
			}
		}
		let url = deleteurl;
		if(!confirm('선택된 항목을 삭제하시겠습니까?')){
			e.preventDefault();
			return false;
		}
		e.preventDefault(); 
		fetch(url,
			{
				method:"POST", 
				headers:{
					'Content-Type': 'application/json',
				},
				body:JSON.stringify(deleteList)
			}).then(res =>{
			if(res.ok){
				alert('삭제성공');
				location.href = outUrl;
			}else{
				alert('삭제실패');
			}
		})	
	});
}
