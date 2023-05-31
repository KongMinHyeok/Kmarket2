document.addEventListener('DOMContentLoaded',()=>{
	//수량 변경 + 자동 총 합계 계산
		$(function(){
			
			let price = parseInt($('input[name=price]').val());
			let discount = parseInt($('input[name=discount]').val());
			let delivery = parseInt($('input[name=delivery]').val());
			let totalPrice = 1 * Math.round(price*(100-discount)/100) + delivery;
			$('.total > span').text(totalPrice.toLocaleString());
			
			$('.increase').on('click', function(){
				let quantity = $(this).parent("div").find("input").val();
				$(this).parent("div").find("input").val(++quantity);
				let count = parseInt($('input[name=num]').val());
				let totalPrice = count * Math.round(price*(100-discount)/100) + delivery;
				$('.total > span').text(totalPrice.toLocaleString());
			});
			
			$(".decrease").on("click", function(){
				let quantity = $(this).parent("div").find("input").val();
				if(quantity > 1){
					$(this).parent("div").find("input").val(--quantity);		
				}
				let count = parseInt($('input[name=num]').val());
				let totalPrice = count * Math.round(price*(100-discount)/100) + delivery;
				$('.total > span').text(totalPrice.toLocaleString());
			});
			
			// 장바구니 클릭
 			$('input[name=cart]').click(function(){
				
				//let uid = $('span[name=uid]').text();
				let uid = "admin";
				let prodNo = $('input[name=prodNo]').val();
				let count = $('input[name=num]').val();
				let price = $('input[name=price]').val();
				let discount = $('input[name=discount]').val();
				let point = $('input[name=point]').val();
				let delivery = $('input[name=delivery]').val();
				let total = $('.total > span').text().replaceAll(',','');
				console.log(uid);
				
				let jsonData = {
						"uid": uid,
						"prodNo": prodNo,
						"count": count,
						"price": price,
						"discount": discount,
						"point": point,
						"delivery": delivery,
						"total": total,
				};
				console.log(jsonData);
				if(uid){
					$.ajax({
						url: '/kmarket2/product/cartInert',
						type: 'post',
						data: JSON.stringify(jsonData),
						dataType: 'json',
						contentType:'application/json',
						success: function(data){
							if(confirm('장바구니에 추가되었습니다.\n장바구니로 이동하시겠습니까?')){
								location.href = "/kmarket2/product/cart";
							}else{
								return;
							}
						}
					});
				}else{
					alert('로그인 후 이용 가능합니다.');
					if(confirm('로그인 페이지로 이동하시겠습니까?')){
						location.href = "/kmarket2/member/login";
					}else{
						return;
					}
				}
			}); 
			
			// 주문하기 클릭
			$('.order').click(function(){
				let count = $('input[name=num]').val();
				let uid = $('span[name=uid]').text();
				let prodNo = $('input[name=prodNo]').val();
				
				if(uid){
					location.href = "/kmarket2/product/order?prodNo="+prodNo+"&count="+count;
				}else{
					alert('로그인 후 이용 가능합니다.');
					if(confirm('로그인 페이지로 이동하시겠습니까?')){
						location.href = "/kmarket2/member/login";
					}else{
						return;
					}
				}
			});
			
		});
		
		
		$(function(){
	
		// 전체선택
		$('.allBox').on('click', function(){
			var chk = $('.allBox').prop('checked');
			if(chk){
				$('.ckBox').prop('checked', true);
			}else{
				$('.ckBox').prop('checked', false);
			}
		});
		
		// 전체 선택 후 개별 체크박스 해제시 전체선택 해제
		$('.ckBox').click(function(){
			$('.allBox').prop('checked', false);
		});
	
		// 선택 삭제
		$('.delete').click(function(){
			
			var chkArr = new Array();
			
			$(".ckBox:checked").each(function(){
				let chk = $(this).val();
				chkArr.push(chk);
			})
			console.log(chkArr);
			
			let chks = chkArr.toString();
			console.log(chks);
			
			let jsonData = {
					"chks":chks
			}
			if(chks.length < 1){
				alert('삭제할 상품을 선택해주세요.');
				return false;
			}
			let isCheck = confirm('정말 삭제하시겠습니까?');
			
			if(isCheck){
				$.ajax({
						url: '/kmarket2/product/cart',
						method: 'post',
						data: jsonData,
						dataType:"json",
						success: function(data){
							if(data.result > 0){
								location.reload();
							}else{
								alert('삭제에 실패하였습니다.');
							}
						}
					});
				}else{
					return;
				}
		});
		
		
		// 총 합계
		$('.allBox, .ckBox').click(function(){
			
			let dis = $('input[name=discount]').val();
			var totalCount = 0;
			var totalPrice = 0;
			var totalDiscount = 0;
			var totalDelivery = 0;
			var totalPoint = 0;
			var totalSum = 0;
			
			$('.ckBox').each(function(){
				if($(this).is(':checked') == true){
					var totalcount = parseInt($(this).parents('tr').find('input[name=count]').val());
					totalCount = totalCount + totalcount;
				}
				if($(this).is(':checked') == true){
					var totalprice = parseInt($(this).parents('tr').find('input[name=price]').val());
					totalPrice = totalPrice + totalprice;
				}
				if($(this).is(':checked') == true){
					var totaldiscount = parseInt($(this).parents('tr').find('input[name=discount]').val());
					totalDiscount = totalDiscount + totaldiscount;
				}
				if($(this).is(':checked') == true){
					var totaldelivery = parseInt($(this).parents('tr').find('input[name=delivery]').val());
					totalDelivery = totalDelivery + totaldelivery;
				}
				if($(this).is(':checked') == true){
					var totalpoint = parseInt($(this).parents('tr').find('input[name=point]').val());
					totalPoint = totalPoint + totalpoint;
				}
				if($(this).is(':checked') == true){
					var total = parseInt($(this).parents('tr').find('input[name=total]').val());
					totalSum = totalSum + total;
				}
				totalDiscount = Math.round(totalPrice*(dis/100));
			});
			
			$('#cartCount').text(totalCount);
			$('#cartPrice').text(Number(totalPrice).toLocaleString());			
			$('#cartDiscount').text('-'+Number(totalDiscount).toLocaleString());			
			$('#cartDelivery').text(Number(totalDelivery).toLocaleString());			
			$('#cartPoint').text(Number(totalPoint).toLocaleString());			
			$('#cartTotal').text(Number(totalSum).toLocaleString()+'원');			
			
		});
		
		
		// 주문하기
		$('.btnOrder').click(function(e){
			e.preventDefault();
			
			let chkArr = [];
			$('input[name=ck]:checked').each(function(){
				let chk = $(this).val();
				chkArr.push(chk);
			})
			if(chkArr.length == 0){
				alert('주문할 상품을 선택해주세요.');
				return false;
			}
			let chks = chkArr.toString();
			
			if(confirm('주문하기로 이동하시겠습니까?')){
				location.href = "/kmarket2/product/order?cartNo="+chks;
			}else{
				return false;
			}
		});
	
	});
	
	$(function() {
		let isOnce = 0;
		
		$('.btnPoint').click(function() {
			let point = $('input[name=point]').val();
			let userPoint = $('.userPoint').text();
			
			if(point < 5000){
				alert('5,000점 이상부터 사용 가능합니다.');
				return false;
			}else if(point >= 5000){
				if(point > userPoint){
					alert('현재 포인트보다 많이 사용할 수 없습니다.');
					return false;
				}
				
				if(isOnce == 0){
					$('.pointDC').text(Number(point).toLocaleString());
					const finTot = parseInt($('.finTot').text().replaceAll(',',''));
					let result = finTot-point;
					$('.pointDC').removeAttr("style");
					$('.finTot').text(result.toLocaleString());
					isOnce = 1;
				}else{
					alert('한 번 적용이 가능합니다.');
					return false;
				}
			}
		});
	
	});

	
	
})
