/**
 * 
 */

idcheck = function(idvalue){
   // 1.공백
	if(idvalue.trim().length <= 0){
		alert("아이디를 입력하세요.")
		return false;
	}
   
   // 2.길이
   if(idvalue.length < 4 || idvalue.length > 12){
      alert("아이디의 길이는 4~12글자 사이로 입력하세요.");
      return false;
   }
//   3.
   // 입력값.match(정규식) - 일치하면 일치되는 값을 리턴 없으면 null을 return
   // 정규식.test(입력값) - 일치하면 true를 리턴 않으면 false를 리턴
   var idRegExp = /^[a-zA-Z]/g;
   if(!idRegExp.test(idvalue)){
	   alert("아이디의 첫글자는 영문이어야 합니다.");
	   return false;
   }
   var idRegExp = /[\^\[\(\|\+\{\.\$\)\*\?\\]+/g;
   if(idRegExp.test(idvalue)){
	   alert("아이디 작성시 특수문자는 '-','_'만 사용 가능합니다.");
	   return false;
   } 
   
   var idRegExp = /^[가-힣ㄱ-ㅎㅏ-ㅣ]+/g;
   if(idRegExp.test(idvalue)){
	   alert("아이디의 첫글자는 영문이어야 합니다.");
	   return false;
   }
   
   var idRegExp = /^[a-zA-Z]{1}[a-z0-9A-Z-_]{3,11}$/g;
   if(!idRegExp.test(idvalue)){
	   alert("아이디 형식 오류!");
	   return false;
   } else {
	   return true;
   }
   

}

datacheck = function(datas){
   // pass, name, hp, mail - 공백 검증과 길이검증, 정규식
   // 이름에 대하여
	
   // 공백체크
	for(i=0 ; i<datas.length ; i++){
		if(datas[i].name == "mem_id"){
			if(!idcheck(datas[i].value)) return false;
		}
		// 이름 체크
		if(datas[i].name == "mem_name"){
			// 이름 공백체크
			if(datas[i].value.trim().length <= 0){
				alert("이름을 입력하세요.")
				return false;
			}
			// 이름 : 한글이름인 경우
			var nameRegExp = /^[가-힣ㄱ-ㅎㅏ-ㅣ]+$/g;
			if(nameRegExp.text(datas[i].value)){
				if(datas[i].value < 2 || datas[i].value > 5){
					alert("한글 이름은 2~5글자 사이로 입니다.");
					return false;
				}
				var nameRegExp = /[가-힣]{2,5}/g;
				if(!nameRegExp.test(datas[i].value)){
					alert("이름 형식 오류!");
					return false;
				} else {
					return true;
				}
			}
			// 이름 : 영문이름인 경우
			var nameRegExp = /^[a-zA-Z]+$/g;
			if(nameRegExp.text(datas[i].value)){
				if(datas[i].value < 5 || datas[i].value > 10){
					alert("영문 이름은 5~10글자 사이로 입니다.");
					return false;
				}
				var nameRegExp = /[a-zA-Z]{5,10}/g;
				if(!nameRegExp.test(datas[i].value)){
					alert("이름 형식 오류!");
					return false;
				} else {
					return true;
				}
			}
			// 이름 : 숫자, 영문, 한글 혼합 입력시
			var nameRegExp = /^[가-힣a-zA-Z0-9\s]+$/g;
			if(nameRegExp.text(datas[i].value)){
				alert("이름은 숫자, 영문, 한글을 혼합하여 입력할 수 없습니다!");
				return false;
			}
		}
		
		
		// 비밀번호 체크
		if(datas[i].name == "mem_pass"){
			// 비밀번호 :  공백체크
			if(datas[i].value.trim().length <= 0){
				alert("비밀번호를 입력하세요.")
				return false;
			}
			// 비밀번호 : 길이 체크
			if(datas[i].value < 4 || datas[i].value > 10){
				alert("비밀번호는 4~10글자 사이로 입니다.");
				return false;
			}
			// 비밀번호 : 조합체크
			var passRegExp = /^[a-zA-Z0-9\^\[\(\|\+\{\.\$\)\*\?\\]{4,10}$/g;
			if(!passRegExp.text(datas[i].value)){
				alert("비밀번호는 숫자, 영문, 특수문자 조합으로 작성해야 합니다.");
				return false;
			} else {
				return true;
			}
		}
		
		// 휴대폰번호 체크
		if(datas[i].name == "mem_hp"){
			// 휴대폰번호 :  공백체크
			if(datas[i].value.trim().length <= 0){
				alert("휴대폰 번호를 입력하세요.")
				return false;
			}
			// 휴대폰번호 : 형식체크
			var hpRegExp = /^(010-?\d{4})|(01[1|6|7|8|9]-?\d{3,4}))-?\d{4}$/g;
			if(!hpRegExp.text(datas[i].value)){
				alert("휴대전화 형식 오류!!('-' 포함 작성)");
				return false;
			} else {
				return true;
			}
		}
		
		// 이메일 체크
		if(datas[i].name == "mem_mail"){
			// 이메일 :  공백체크
			if(datas[i].value.trim().length <= 0){
				alert("이메일을 입력하세요.")
				return false;
			}
			// 이메일 : 형식체크
			var mailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			if(!mailRegExp.text(datas[i].value)){
				alert("이메일 형식 오류!!('-' 포함 작성)");
				return false;
			} else {
				return true;
			}
		}
	}
}
   