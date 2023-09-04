function renderData(data) {
    const dataContainer = document.getElementById('dataContainer');

    // 데이터가 있는지 확인
    if(data && data.length  > 0) {
        data.forEach((item) => {
            // 데이터를 화면에 표시
            const dataItem = document.createElement('div');
            // 데이터를 형식 설정
            dataItem.textContent = `${item.id}: ${item.name}`;
            dataContainer.appendChild(dataItem);
        });
    } else  {
        // 데이터가 없는 경우
        dataContainer.textContent = "No Data Available!!";
    }
}

// 서버로 부터 데이터를 로딩하여 렌더링
fetch('http://localhost:8080/api/sample')// 실제 API URL로 대체해야 함
    .then(response => response.json())
    .then(data => renderData(data))
    .catch(error => {
        console.error("Error fetching data: ", error);
        const dataContainer = document.getElementById('dataContainer')
        dataContainer.textContent = "Error fetching data.";
    });