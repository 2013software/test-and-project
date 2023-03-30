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






const root=document.documentElement;
const dropdown_title_icon=document.querySelector(".dropdown-title-icon");
const dropdown_title=document.querySelector(".dropdown-title");
const dropdown_list=document.querySelector(".dropdown-list");
const main_button=document.querySelector(".main-button");
const floating_icon=document.querySelector(".floating-icon");


const icons= {};
// 下拉列表项
const list_items=["8:00","10：00","12：00","15：00","17：00"];


// 下拉列表项模板
const listItemTemplate=(text,translate_value)=>{
  return `
        <li class="dropdown-list-item">
            <button class="dropdown-button list-button" data-translate-value="${translate_value}%">
                <span class="text-truncate">${text}</span>
            </button>
        </li>
    `;
}

// 生成下拉列表项
const renderListItems=()=>{
  dropdown_list.innerHTML+=list_items.map((item,index)=>{
    return listItemTemplate(item,100*index);
  }).join("");
}

// 在页面加载事件中执行生成下拉列表项
window.addEventListener("load",()=>{
  renderListItems();
})

// 设置自定义属性的值
const setDropdownProps=(deg,ht,opacity)=>{
  root.style.setProperty("--rotate-arrow",deg!==0?deg+"deg":0);
  root.style.setProperty("--dropdown-height",ht!==0?ht+"rem":0);
  root.style.setProperty("--list-opacity",opacity);
}

// 下拉框按钮点击事件
main_button.addEventListener("click",()=>{
  const list_wrapper_sizes=3.5;
  const dropdown_open_height=4.6*list_items.length+list_wrapper_sizes;
  const curr_dropdown_height=root.style.getPropertyValue("--dropdown-height")||0;
  curr_dropdown_height==="0"?setDropdownProps(180,dropdown_open_height,1):setDropdownProps(0,0,0);
})

// 下拉列表项鼠标移入事件
dropdown_list.addEventListener("mouseover",(e)=>{
  const translate_value=e.target.dataset.translateValue;
  root.style.setProperty("--translate-value",translate_value);
})

// 下拉列表项点击事件
dropdown_list.addEventListener("click",(e)=>{
  const clicked_item_text=e.target.innerText.toLowerCase().trim();

  dropdown_title.innerHTML=clicked_item_text;
  setDropdownProps(0,0,0);
})

// 下拉列表项鼠标移动事件
dropdown_list.addEventListener("mousemove",(e)=>{
  const icon_size=root.style.getPropertyValue("--floating-icon-size")||0;
  const x=e.clientX-dropdown_list.getBoundingClientRect().x;
  const y=e.clientY-dropdown_list.getBoundingClientRect().y;
  const targetText=e.target.innerText.toLowerCase().trim();
  const hover_item_text=icons[targetText];
  floating_icon.innerHTML=iconTemplate(hover_item_text);
  root.style.setProperty("--floating-icon-left",x-icon_size/2+"px");
  root.style.setProperty("--floating-icon-top",y-icon_size/2+"px");
})



var a = document.querySelector('#A');
var div = document.querySelector('.title');
var close = document.querySelector('#close');
var bg = document.querySelector('.bg');
var login = document.querySelector('.login');
var username = document.querySelector('.username');
var rusername = document.querySelector('.rusername');
var iusername = document.querySelector('#iusername');

a.onclick=function(){
  bg.style.display = 'block';
  login.style.display='block';
}
close.onclick=function(){
  bg.style.display = 'none';
  login.style.display='none';
}

div.addEventListener('mousedown',function(e){
  var x = e.pageX-login.offsetLeft;
  var y = e.pageY-login.offsetTop;
  document.addEventListener("mousemove",move)
  function move(e){
    var newx=e.pageX-x;
    var newy=e.pageY-y;
    login.style.left=newx+'px';
    login.style.top=newy+'px';
  }
  document.addEventListener('mouseup',function(e){
    document.removeEventListener("mousemove",move);
  })
})
