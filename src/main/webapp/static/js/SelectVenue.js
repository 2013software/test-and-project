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

const select=document.querySelector(".select")
const options_list=document.querySelector(".options-list")
const options=document.querySelectorAll(".option")
select.addEventListener("click",()=>{
  options_list.classList.toggle("active");
  select.querySelector(".fa-angle-down").classList.toggle("fa-angle-up")
})
options.forEach((option)=>{
  option.addEventListener("click",()=>{
    options.forEach((option)=>{option.classList.remove("selected")});
    select.querySelector("span").innerHTML=option.innerHTML;
    option.classList.add("selected");
    options_list.classList.toggle("active")
    select.querySelector(".fa-angle-up").classList.toggle("fa-angle-up")
  })
})
