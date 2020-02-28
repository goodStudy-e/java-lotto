# java-lotto
로또 미션 진행을 위한 저장소

## 미션 요구사항 

![image](https://user-images.githubusercontent.com/47850258/74334640-a637b000-4ddd-11ea-879f-812470f5c59f.png)

![image](https://user-images.githubusercontent.com/47850258/74334649-ab94fa80-4ddd-11ea-8414-4c89e4363d3a.png)

![image](https://user-images.githubusercontent.com/47850258/74334656-adf75480-4ddd-11ea-8384-cc40b55fcb51.png)

## 필요한 기능
모든 예외처리는 다시 해당 기능을 수행하도록 처리한다.

<ol>
<li>로또 구입 금액을 입력받는기능<br>
* 입력 금액이 1,000원의 배수가 아닐 경우 예외처리</li>

<li>로또 구입 금액에 맞게 로또를 발급하는 기능 <br>
* 중복되는 로또 번호가 있을 경우 예외처리<br>
* 로또번호의 범위가 1 ~ 45를 벗어나는 경우 예외처리 
</li>
<li>당첨번호, 보너스볼 입력받는 기능 <br>
* 당첨번호중 같은 숫자가 존재하는 경우 예외처리<br>
* 당첨번호의 범위가 1 ~ 45를 벗어나는 경우 예외처리<br>
* 입력받은 보너스볼의 숫자가 당첨번호와 중복되는 경우 예외처리<br>
* 보너스볼의 범위가 1 ~ 45를 벗어나는 경우 예외처리<br>
</li>
<li>로또의 당첨 여부(등수, 당첨금)를 확인하는 기능 </li>
<li>총 수익률을 계산하는 기능 <br>
* 구매금액이 0인 경우 예외처리<br></li>
<li>로또 등수에 따른 당첨 갯수와 수익률 메시지 출력하는 기능</li>
</ol>




