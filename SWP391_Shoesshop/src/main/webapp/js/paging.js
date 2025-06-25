function paging(listTmp) {
    $(function () {
        (function (name) {
            var container = $('#pagination-' + name);
            if (!container.length)
                return;
            var options = {
                dataSource: listTmp,
                pageSize: 15,
                showGoInput: true,
                showGoButton: true,
                showNavigator: false,
                showSizeChanger: false,
                formatNavigator: '< rangeStart >-< rangeEnd > of < totalNumber > items',
                callback: function (response, pagination) {
                    var dataHtml = '<main>';
                    $.each(response, function (index, item) {
                        dataHtml +=
                              `<div class="product-card" style="box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;" data-product-url="MainController?action=Detail&pid=${item.id}">
                                <div class="product-front">
                                  <img src="${item.image}" alt="" style="object-fit: fill"/>
                                  <div class="image_overlay"></div>
                                  <div class="product-description">
                                    <p style="color: white; font-size: 14px; padding: 15px 20px; text-align: center; line-height: 1.4; margin: 0; font-weight: 600; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8); letter-spacing: 0.3px;">
                                      ${item.shortDescription || 'Sản phẩm chất lượng cao với thiết kế hiện đại và thoải mái, mang đến trải nghiệm tuyệt vời cho người sử dụng.'}
                                    </p>
                                  </div>
                                  <div class="stats">
                                  <div class="stats-container">
                                    <span class="product_name">${item.name}</span><br>
                                    <span class="product_price">
                                    ${item.originalPrice !== item.discountPrice ? `<del style="margin-right: 5px">${item.originalPrice.toLocaleString('en-US', { style: 'currency', currency: 'USD' })}</del>` : ''}
                                    <span style="background: #16c6c6; color: white; padding: 5px; border-radius: 5px">${item.discountPrice.toLocaleString('en-US', { style: 'currency', currency: 'USD' })}</span>
                                    </span>
                                    <p style="margin-bottom: 20px; margin-top: 10px;">
                                    ${item.status === 'INSTOCK' ? '<span class="text-success" style="font-weight: 700; font-size: 16px">Còn hàng</span>' : ''}
                                    ${item.status === 'OUTOFSTOCK' ? '<span class="text-danger" style="font-weight: 700; font-size: 16px">Hết hàng</span>' : ''}
                                    </p>
                                  </div>
                                  </div>
                                </div>
                                </div>`;
                    });
                    dataHtml += '</main>';
                    container.prev().html(dataHtml);
                    let listProductName = document.querySelectorAll('.stats-container .product_name');
                    listProductName.forEach(name => {
                        if (name.innerHTML.length >= 26) {
                            name.style = 'font-size: 15.5px !important';
                        }
                    });
                    const targetDiv = document.querySelector('.data-container');
                    const position = targetDiv.getBoundingClientRect().top + window.pageYOffset - 300;
                    const pageChoices = document.querySelectorAll('.paginationjs-pages ul li a');
                    pageChoices.forEach(choice => {
                        choice.addEventListener('click', () => {
                            window.scrollTo({
                                top: position,
                                behavior: "smooth"
                            });
                        });
                    });
                    const goButton = document.querySelector('.paginationjs .paginationjs-go-button>input[type=button]');
                    const pages = Math.ceil(listTmp.length / options.pageSize);
                    goButton.addEventListener('click', () => {
                        const inputPagingValue = document.querySelector('.paginationjs .paginationjs-go-input>input[type=text]').value;
                        if (inputPagingValue <= pages) {
                            window.scrollTo({
                                top: position,
                                behavior: "smooth"
                            });
                        }
                    });
                    cardHover();
                    
                    // Add click event to product cards for navigation
                    const productCards = document.querySelectorAll('.product-card[data-product-url]');
                    productCards.forEach(card => {
                        card.addEventListener('click', function() {
                            const url = this.getAttribute('data-product-url');
                            if (url) {
                                window.location.href = url;
                            }
                        });
                        
                        // Add pointer cursor to indicate clickable
                        card.style.cursor = 'pointer';
                    });
                }
            };
            container.pagination(options);
        })('main');
    });
};


