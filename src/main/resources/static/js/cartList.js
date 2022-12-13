/**
 * 
 */
 
  window.addEventListener('DOMContentLoaded', () => {
   readCartDesc();

  
    
    

    
   function readCartDesc(){
        const userid = document.querySelector('#userId').value;
        
        axios.get('api/cart/all/' + userid) 
        .then(response => { updateCartList(response.data)
        
        })
        .catch(err => {console.log(err) });
        
        console.log('전체 페이지 읽기 ajax 함수')
        
    };
    
    
    
    function updateCartList(data){
        console.log('카트 리스트 전체 보기 함수 data 받음 ')
        const divCart = document.querySelector('#cList')
        let str = '';
        
        str += '<table class="w-100 table" style="text-align: center;"> '
        + '<thead class="table-light"> '
         +  '  <tr> '
          +      ' <th colspan="2">도서 정보</th> '
           +   '   <th>수량</th> '
            +  '   <th>주문금액</th> '
            +   '  <th>배송비</th> '
            +  '  <th></th> '
          + '  </tr> '
      + '  </thead> '
     + '   <tbody style="height: 200px;"> ';
        
        
        for(let c of data){
        
      str  += '<tr>'
            +  '<td class="align-middle">' 
            +   ' <form id="formCheck">' 
            +   ' <input type="checkbox"  id="ckBox" style="width: 30px;"  name="cartId"  value="'+ c.cartId +'"/>' 
            +   ' </form>' 
            
            +   ' <img src="' + c.image +'" style="width: 150px;"/></td>' 
            +   ' <td class="align-middle" style="text-align: left;"><!-- 도서 간략 정보 -->' 
            +              '  <small class="d-inline-flex px-2 my-1 border rounded text-secondary">' 
            +                 '   <span>'+c.group+'</span><span> / </span><span>'+c.category+'</span>' 
            +              '  </small>' 
            +              '  <div class="h5">'+c.title+'</div>' 
            +             '   <div >'+c.author+'</div>' 
            +              '  <div>'+c.prices 
            +                '    <span>원</span>' 
            +                 '   <small class="px-1 border border-primary rounded text-primary">p</small>' 
            +                  '  <small class="text-primary">'+c.prices*0.05+'</small> ' 
            +               ' </div>' 
            +  '  </td>' 
            +  '  <div>' 
            +  '  <td class="align-middle">' 
            +      '  <input type="button" class="btnPlusMinus"  value="+"/>' 
            +     '   <span style="width: 50px" id="countS" >'+c.count +' </span>' 
            +       ' <input type="button" class="btnPlusMinus"  value="-"/>' 
    
            +  '  </td>' 
            +   ' <td class="align-middle">' 
            +  '  <div class="selectPrice">' 
            +      '  <div id="price">' 
            +        c.prices+ '<span>원</span>' 
            +     '   </div>' 
            +   ' </div>' 
            +  '  </td>' 
            +  '  <td class="align-middle">10,000원 이상<br/>' 
            +   '     배송비 무료</td>' 
            +  '  <td class="align-middle">' 
            +      '  <button type="button" class="btn btn-dark btn-sm my-2" style="width: 100px;">Buy Now</button><br/>' 
            +     '   <button type="button" class="btn btn-danger btn-sm my-2" style="width: 100px;">Delete</button>' 
            +  '  </td>'
            + '</tr>'; 
        }
        
       str 
            += '</tbody>'
            + '</table>';
            
            
        
        divCart.innerHTML = str;
        
        
    }
    
    
    
    const btnDelete = document.querySelector('#btnDelete')
    
    btnDelete.addEventListener('click', function(){
    const userId = document.querySelector('#userId').value;
    console.log(userId)
    
    const list = document.querySelectorAll('#ckBox');
    let ckList = [];
    
        
        for(let i=0; i<list.length; i++ ){
            if(list[i].checked){
                let a = list[i].value;
                ckList.push(a);
            }
            
        }
        ckList.push(userId)
        
        
        const result = confirm('장바구니를 삭제?')
        
        if(result){
            axios
            .post('api/cartid', ckList)
            .then(response => {
                updateCartList(response.data)
                console.log(response.data);
            })
            .catch(err => {console.log(err)})
        }
        
        }) 
        

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
 })







