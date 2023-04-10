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
const coursename = document.getElementById('course-name');
const number = document.getElementById('student-number');

form.addEventListener('submit', e => {
  e.preventDefault();
  validateForm();
});

// 表单验证
function validateForm() {
  const nameValue = coursename.value.trim();
  const number11111 = number.value.trim();

  // 验证
  if(nameValue === '') {
    printError(coursename, 'Please enter the course name');
  } else {
    removeError(coursename);
  }


  // 验证
  if(number11111 === '') {
    printError(number, 'Please enter student number');
  }else if (isNaN(Number(number.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
    printError(number, 'Correct student numbers should be pure numbers!')
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

