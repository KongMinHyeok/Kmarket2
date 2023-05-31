$(()=>{
	
	
	
});

/*$.validator.addMethod("regexUid", function(value, element, regexp){
	let regex = new RegExp(regexp);
	let result = regex.test(value);
	return result;
});*/

const validateForm = () => {
	$('form').validate({
		rules:{
			/* 공통 회원가입 */
			uid:{
				required: true,
				pattern: "^[a-zA-Z0-9]{4,12}$",
				remote:{
					url: "/kmarket2/member/checkUid",
					type: "post",
				}
			},
			pass1:{
				required: true,
				pattern: "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$",
				
			},
			pass2:{
				required: true,
				equalTo: "#pass1",
			},
			name:{
				required: true,
				pattern: "^([가-힣a-zA-Z]{2,20})$",
			},
			gender:{
				required: true,
			},
			email:{
				required: true,
				email: true,
				remote:{
					url: "/kmarket2/member/checkEmail",
					type: "post",
				}
			},
			hp:{
				required: true,
				pattern: "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
				remote:{
					url: "/kmarket2/member/checkHp",
					type: "post",
				}
			},
			
			/* 판매자 회원가입 */
			company:{
				pattern: /^\(주\).{0,18}$/,
			},
			ceo:{
				pattern: "^([가-힣a-zA-Z]{2,20})$",
			},
			bizRegNum:{
				pattern: /^\d{3}-\d{2}-\d{5}$/,
			},
			comRegNum:{
			},
			tel:{
				pattern: /^\d{2,3}-\d{3,4}-\d{4}$/,
			},
			fax:{
				pattern: /^\d{2,3}-\d{3,4}-\d{4}$/,
			},
			manager:{
				pattern: "^([가-힣a-zA-Z]{2,20})$",
			},
			managerHp:{
				pattern: "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
			}
			
		},
		
		messages:{
			uid:{
				required: "아이디는 필수입력사항입니다.",
				pattern: "영문, 숫자로 4~12자까지 설정해 주세요.",
				remote: "이미 존재하는 아이디입니다.",
			},
			pass1:{
				required: "비밀번호는 필수입력사항입니다.",
				pattern: "영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.",
			},
			pass2:{
				required: "비밀번호는 필수입력사항입니다.",
				equalTo: "비밀번호가 일치하지 않습니다.",
			},
			name:{
				required: "이름은 필수입력사항입니다.",
				pattern: "이름은 2자이상 한글 또는 영문이어야합니다.",
			},
			gender:{
				required: "성별은 필수입력사항입니다.",
			},
			email:{
				required: "이메일은 필수입력사항입니다.",
				email: "이메일 형식을 확인해주세요",
				remote: "이미 존재하는 이메일입니다.",
			},
			hp:{
				required: "휴대폰 번호는 필수입력사항입니다.",
				pattern: "- 포함 13자리를 입력하세요.",
				remote: "이미 존재하는 번호입니다.",
			},

			/* 판매자 회원가입 */
			company:{
				required: "회사명은 필수 입력사항입니다.",
				pattern: "(주)포함 입력, 예) (주)케이마켓",
			},
			ceo:{
				required: "대표자는 필수 입력사항입니다.",
				pattern: "2자이상 한글 또는 영문이어야합니다.",
			},
			bizRegNum:{
				required: "사업자 등록번호는 필수 입력사항입니다.",
				pattern: "-표시 포함, 12자리 입력, 예) 123-45-67890",
			},
			comRegNum:{
				required: "통신판매업신고 번호는 필수 입력사항입니다.",
			},
			tel:{
				required: "전화번호는 필수 입력사항입니다.",
				pattern: "- 표시 포함, 지역번호 포함, 예) 02-234-1234",
			},
			fax:{
				required: "팩스번호는 필수 입력사항입니다.",
				pattern: "- 표시 포함, 지역번호 포함, 예) 02-234-1234",
			},
			manager:{
				required: "담당자명은 필수 입력사항입니다.",
				pattern: "2자이상 한글 또는 영문이어야합니다.",
			},
			managerHp:{
				required: "담당자 번호는 필수 입력사항입니다.",
				pattern: "- 포함 13자리를 입력하세요.",
			}
		},
        errorPlacement: function(err, element){
          if(element.is(":radio") || element.is(":checkbox")){
              element.parent().after(err);
          } else {
              element.after(err);
          }
        },
	});
	
}

function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("addr2").value = extraAddr;
                
                } else {
                    document.getElementById("addr2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zip').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }