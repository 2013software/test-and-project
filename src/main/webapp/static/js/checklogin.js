const inputs = document.querySelectorAll(".input")
const loginform = document.getElementById('login-form');
const phonenumber = document.getElementById('TNumber');
const smscode = document.getElementById('SMS-code');

function addcl(){
  let parent = this.parentNode.parentNode;
  parent.classList.add("focus")
}

function remcl(){
  let parent = this.parentNode.parentNode;
  if(this.value === ""){
    parent.classList.remove("focus")
  }
}

inputs.forEach(input => {
  input.addEventListener("focus",addcl);
  input.addEventListener("blur",remcl);
})


loginform.addEventListener('submit', e => {
  e.preventDefault();
  validateForm();
});

function validateForm() {
  const number1 = phonenumber.value.trim();
  const number2 = smscode.value.trim();

  if(number1 === '') {
    printError(phonenumber, 'Please enter your telephone number');
  }else if (isNaN(Number(phonenumber.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
    printError(phonenumber, 'Please enter right phone number!')
  }else{
    removeError(phonenumber);
  }


  if(number2 === '') {
    printError(smscode, 'Please enter SMS verification code');
  }else if (isNaN(Number(smscode.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
    printError(smscode, 'Please enter right SMS!')
  }else{
    removeError(smscode);
  }

}

// 打印错误提示
function printError(input, message) {
  const formControl = input.parentElement;
  const errorMessage = formControl.querySelector('.error-message');
  formControl.classList.add('error');
  errorMessage.textContent = message;
}

// 删除错误提示（用户输入正确信息）
function removeError(input) {
  const formControl = input.parentElement;
  formControl.classList.remove('error');
}
