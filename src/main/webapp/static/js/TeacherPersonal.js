class MobileNavbar {
  constructor(mobileMenu, navList, navLinks) {
    this.mobileMenu = document.querySelector(mobileMenu);
    this.navList = document.querySelector(navList);
    this.navLinks = document.querySelectorAll(navLinks);
    this.activeClass = "active";
    this.handleClick = this.handleClick.bind(this);
  }

  animateLinks() {
    this.navLinks.forEach((link, index) => {
      link.style.animation
        ? (link.style.animation = "")
        : (link.style.animation = `navLinkFade 0.5s ease forwards ${
          index / 7 + 0.3
        }s`);
    });
  }

  handleClick() {
    this.navList.classList.toggle(this.activeClass);
    this.mobileMenu.classList.toggle(this.activeClass);
    this.animateLinks();
  }

  addClickEvent() {
    this.mobileMenu.addEventListener("click", this.handleClick);
  }

  init() {
    if (this.mobileMenu) {
      this.addClickEvent();
    }
    return this;
  }
}

const mobileNavbar = new MobileNavbar(
  ".mobile-menu",
  ".nav-list",
  ".nav-list li",
);
mobileNavbar.init();


const form = document.getElementById('form');
const TID = document.getElementById('teacher-id');
const theusername = document.getElementById('username');
const thepassword = document.getElementById('password');
const number = document.getElementById('phone-number');

form.addEventListener('submit', e => {
  e.preventDefault();
  validateForm();
});

// 表单验证
function validateForm() {
  const usernameValue = TID.value.trim();
  const emailValue = theusername.value.trim();
  const passwordValue = thepassword.value.trim();
  const passwordConfirmValue = number.value.trim();

  // 验证
  if(usernameValue === '') {
    printError(TID, 'Please enter the ID');
  } else if (isNaN(Number(TID.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
    printError(TID, 'Correct Teacher ID should be pure numbers!')
  }else {
    removeError(TID);
  }

  // 验证
  if(emailValue === '') {
    printError(theusername, 'Please enter the Username');
  }  else {
    removeError(theusername);
  }

  // 验证
  if(passwordValue === '') {
    printError(thepassword, 'Please enter Password');
  } else {
    removeError(thepassword);
  }

  // 验证
  if(passwordConfirmValue === '') {
    printError(number, 'Please enter Telephone');
  } else if (isNaN(Number(number.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
    printError(number, 'Please enter the correct phone number!')
  }else{
    removeError(number);
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

