<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<style>
        table { width: 80%; margin: auto; }
        th, td { border: 1px solid #aaa; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { margin: 0 5px; }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1>📦 상품 관리</h1>
    <table>
        <thead>
            <tr>
                <th>상품번호</th>
                <th>상품명</th>
                <th>가격</th>
                <th>재고</th>
            </tr>
        </thead>
        <tbody id="productTbody"></tbody>
    </table>

    <hr>
    <form id="productForm" onsubmit="return false;">
        <input type="text" id="productId" placeholder="상품번호" readonly><br>
        <input type="text" id="name" placeholder="상품명"><br>
        <input type="text" id="price" placeholder="가격"><br>
        <input type="text" id="stockQuantity" placeholder="재고"><br>
    </form>

    <button type="button" id="btnInsert">등록</button>
    <button type="button" id="btnUpdate">수정</button>
</div>

<script>
window.onload = function(){
    fetchProductList();

    document.getElementById("btnInsert").onclick = insertProduct;
    document.getElementById("btnUpdate").onclick = updateProduct;
}

// 상품 목록 불러오기
async function fetchProductList() {
    try {
        let response = await fetch('/PhoneManagerAjax/product/list');
        let data = await response.json();
        console.log("✅ 응답 데이터:", data);
        renderProductTable(data);
    } catch (error) {
        console.error(error);
        alert('상품 목록 처리 중 오류 발생!');
    }
}

// 테이블 렌더링
function renderProductTable(list) {
    console.log("📦 상품 리스트 도착:", list);
    let html = ""; // ← ✅ 반복문 바깥에서 선언

    list.forEach(product => {
        console.log("🧱 개별 product:", product);
        const priceStr = Number(product.price).toLocaleString();
        html += `
            <tr style="cursor:pointer"
                data-id="${product.product_id}"
                data-name="${product.name}"
                data-price="${product.price}"
                data-stock="${product.stock_quantity}">
                <td>\${product.product_id}</td>
                <td>\${product.name}</td>
                <td>\${priceStr}원</td>
                <td>\${product.stock_quantity}</td>
            </tr>
        `;
    });

    const tbody = document.getElementById("productTbody");
    tbody.innerHTML = html;

    console.log("✅ 최종 HTML:", html);
    
    tbody.querySelectorAll("tr").forEach(row => {
        row.onclick = function () {
            document.getElementById("productId").value = this.dataset.id;
            document.getElementById("name").value = this.dataset.name;
            document.getElementById("price").value = this.dataset.price;
            document.getElementById("stockQuantity").value = this.dataset.stock;
        };
    });
}





// 상품 등록
async function insertProduct() {
    const name = document.getElementById("name").value;
    const price = document.getElementById("price").value;
    const stock = document.getElementById("stockQuantity").value;

    if (!name || !price || !stock) {
        alert("모든 값을 입력하세요.");
        return;
    }

    const params = new URLSearchParams({ name, price, stock });
    try {
        let res = await fetch('/PhoneManagerAjax/product/insert', {
            method: "POST",
            body: params
        });
        let data = await res.json();
        alert('상품 등록 완료!');
        fetchProductList();
        resetForm();
    } catch (err) {
        console.error(err);
        alert("상품 등록 오류!");
    }
}

// 상품 수정
async function updateProduct() {
    const productId = document.getElementById("productId").value;
    const name = document.getElementById("name").value;
    const price = document.getElementById("price").value;
    const stock = document.getElementById("stockQuantity").value;

    if (!productId) {
        alert("수정할 상품을 먼저 선택하세요.");
        return;
    }

    const params = new URLSearchParams({ productId, name, price, stock });
    try {
        let res = await fetch('/PhoneManagerAjax/product/update', {
            method: "POST",
            body: params
        });
        let data = await res.json();
        alert(data.result === 'success' ? '상품 수정 완료!' : '수정 실패!');
        fetchProductList();
        resetForm();
    } catch (err) {
        console.error(err);
        alert("상품 수정 오류!");
    }
}

// 입력폼 초기화
function resetForm() {
    document.getElementById("productForm").reset();
    document.getElementById("productId").value = "";
}
</script>
</body>
</html>
