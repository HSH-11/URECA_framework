<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 관리</title>
<style>
        table { width: 80%; margin: auto; }
        th, td { border: 1px solid #aaa; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { margin: 0 5px; }
    </style>
</head>
<body> 
<div style="text-align: center;">
	<h1>📋 고객 관리</h1>
    <table>
        <thead>
            <tr>
                <th>고객번호</th>
                <th>고객명</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>주소</th>
            </tr>
        </thead>
        <tbody id="customerTbody">
        </tbody>
     </table>
     <hr>  
       <form> 
		<input type="text" name="customerId" id="customerId"></input><br>
		<input type="text" name="name" id="name" ></input><br>
		<input type="text" name="email" id="email"></input><br>
		<input type="text" name="phone" id="phone"></input><br>
		<input type="text" name="address" id="address"></input><br>
	</form>
	
	<hr>
	<button type="button" id="btnInsert">등록</button> <button type="button" id="btnUpdate">수정</button> <button type="button" id="btnDelete">삭제</button>
    </div> 
  	<script>
		window.onload = function(){
	        listcustomer();
	        
	        document.querySelector("#btnInsert").onclick = insertcustomer;
	        document.querySelector("#btnUpdate").onclick = updatecustomer;
	        document.querySelector("#btnDelete").onclick = deletecustomer;
	    }
		
		async function listcustomer(){
		            
		            let url = '/PhoneManagerAjax/customer/list';
		            try{
		           	 	// 비동기 처리
		                let response = await fetch(url); // get 요청 json 포함 응답
		                let data = await response.json(); // response에서 json 꺼내서 javascript 객체로 변환
		                
		                makeListHtml(data); // javascript 객체를 이용해서 table에 목록 구성
		                
		            }catch(error){
		                console.log(error);
		                alert('고객 목록 처리 중 오류 발생!');
		            }           
		      }
		
		async function makeListHtml(list){
            console.log(list);
            
            let listHTML = ``; 
            list.forEach( el => {
                listHTML +=
                    `<tr style="cursor:pointer" data-customerId=\${el.customerId}>
                    	<td>\${el.customerId}</td>
                        <td>\${el.name}</td>
                        <td>\${el.email}</td>
                        <td>\${el.phone}</td>
                        <td>\${el.address}</td>
                    </tr>`;
                
            } );
            
            document.querySelector("#customerTbody").innerHTML = listHTML;
                        
            document.querySelectorAll("#customerTbody tr").forEach( el => {
                el.onclick = function(){
                    let customerId = this.getAttribute("data-customerId");    
                    detailcustomer(customerId);
                }
            });
        }
		
		async function detailcustomer(customerId){
            let url = '/PhoneManagerAjax/customer/detail?customerId=' + customerId;
            try{
                let response = await fetch(url);
                let data = await response.json();       
                console.log(data);
                
                document.querySelector("#customerId").value = data.customerId;
                document.querySelector("#customerName").value = data.customerName;
                document.querySelector("#publisher").value = data.publisher;
                document.querySelector("#price").value = data.price;
                
            }catch( error ){
                console.error( error );
                alert('고객 상세 처리 중 오류 발생!');
            }           
        }
		
		async function insertcustomer(){
			let urlParams = new URLSearchParams({ //post 전송 방식 중 x-www-url-encoded 방식
                name: document.querySelector("#name").value,
                email: document.querySelector("#email").value,
                phone: document.querySelector("#phone").value,
                address: document.querySelector("#address").value,
            });
            
            let fetchOptions = {
                method: "POST",
                body: urlParams,
            }
            
            let url = '/PhoneManagerAjax/customer/insert';
            
            try{
                let response = await fetch(url, fetchOptions );
                let data = await response.json();
                alert('고객 등록!');
                
                listcustomer();
                
            }catch( error ){
                console.error( error );
                alert('고객 등록 처리 중 오류 발생!');
            }
		}
		
		async function updatecustomer(){
			let urlParams = new URLSearchParams({
                customerId: document.querySelector("#customerId").value,
                name: document.querySelector("#name").value,
                email: document.querySelector("#email").value,
                phone: document.querySelector("#phone").value,
                address: document.querySelector("#address").value,
            });
            
            let fetchOptions = {
                method: "POST",
                body: urlParams,
            }
            
            let url = '/PhoneManagerAjax/customer/update';
            
            try{
                let response = await fetch(url, fetchOptions );
                let data = await response.json();
                
                console.log(data);
                
                if (data.result == "success"){
                	alert('고객 수정!');
                }else{
                	alert('고객 수정 실패!');
                }
                      
                listcustomer();
                
            }catch( error ){
                console.error( error );
                alert('고객 수정 처리 중 오류 발생!');
            }               
		}
		
		async function deletecustomer(){
			let customerId = document.querySelector("#customerId").value;
            let url = '/PhoneManagerAjax/customer/delete?customerId=' + customerId;
            try{
                let response = await fetch(url);
                let data = await response.json();       
                alert('고객 삭제!');
                
                listcustomer();
                
            }catch( error ){
                console.error( error );
                alert('고객 삭제 처리 중 오류 발생!');
            }     
		}
	</script>
</body>
</html>