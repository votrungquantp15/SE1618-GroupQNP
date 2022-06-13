/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(() => {
    $("tr").each((index, element) => {
        var total = 0;
        let fieldPrice = $(element).find('#fieldPrice').text();
        total += parseFloat(fieldPrice);
        let foodPrice = $(element).find("#foodPrice").text();
        total += parseFloat(foodPrice);

        $(element).find("#total").html(total);
    });
    });
