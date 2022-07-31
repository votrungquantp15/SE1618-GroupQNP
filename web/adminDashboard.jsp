<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="dto.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <meta name="robots" content="">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta name="description" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:title" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:description" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:image" content="https://zenix.dexignzone.com/xhtml/social-image.png">
        <meta name="format-detection" content="telephone=no">
        <title>Admin Dashboard </title>
        <!-- Favicon icon -->
        <link rel="icon" href="data:image/png;base64,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">

        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Custom Stylesheet -->
        <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <jsp:include page="navbarAdmin.jsp"></jsp:include>

        <div class="content-body">
            <div class="container-fluid">
                <div class="form-head mb-sm-5 mb-3 d-flex flex-wrap align-items-center">
                    <h2 class="font-w600 title mb-2 mr-auto ">Dashboard</h2>
                </div>
                <div class="row">
                    <div class="col-xl-3 col-sm-6">
                        <div class="card card-coin">
                            <div class="card-body text-center">
                                <svg style="height: 80px" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" viewBox="0 0 285.5 285.5" style="enable-background:new 0 0 285.5 285.5;" xml:space="preserve">
                                <g id="XMLID_470_">
                                <path id="XMLID_472_" d="M79.999,62.75c0,34.601,28.149,62.75,62.751,62.75s62.751-28.149,62.751-62.75S177.352,0,142.75,0   S79.999,28.149,79.999,62.75z"/>
                                <path id="XMLID_473_" d="M42.75,285.5h200c8.284,0,15-6.716,15-15c0-63.411-51.589-115-115-115s-115,51.589-115,115   C27.75,278.784,34.466,285.5,42.75,285.5z"/>
                                </g>
                                </svg>
                                <h2 class="text-black mb-2 font-w600">Total User</h2>
                                <h3 class="text-black mb-2 font-w600">13</h3>
                                <p class="mb-0 fs-14">
                                    <svg width="29" height="22" viewbox="0 0 29 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <g filter="url(#filter0_d1)">
                                    <path d="M5 16C5.91797 14.9157 8.89728 11.7277 10.5 10L16.5 13L23.5 4" stroke="#2BC155" stroke-width="2" stroke-linecap="round"></path>
                                    </g>
                                    <defs>
                                <filter id="filter0_d1" x="-3.05176e-05" y="-6.10352e-05" width="28.5001" height="22.0001" filterunits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                    <feflood flood-opacity="0" result="BackgroundImageFix"></feflood>
                                    <fecolormatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"></fecolormatrix>
                                    <feoffset dy="1"></feoffset>
                                    <fegaussianblur stddeviation="2"></fegaussianblur>
                                    <fecolormatrix type="matrix" values="0 0 0 0 0.172549 0 0 0 0 0.72549 0 0 0 0 0.337255 0 0 0 0.61 0"></fecolormatrix>
                                    <feblend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow"></feblend>
                                    <feblend mode="normal" in="SourceGraphic" in2="effect1_dropShadow" result="shape"></feblend>
                                </filter>
                                </defs>
                                </svg>
                                <span class="text-success mr-1">10%</span>This week
                                </p>	
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6">
                        <div class="card card-coin">
                            <div class="card-body text-center">
                                <svg style="height: 80px" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" width="72.371px" height="72.372px" viewBox="0 0 72.371 72.372" style="enable-background:new 0 0 72.371 72.372;" xml:space="preserve">
                                <g>
                                <path d="M22.57,2.648c-4.489,1.82-8.517,4.496-11.971,7.949C7.144,14.051,4.471,18.08,2.65,22.568C0.892,26.904,0,31.486,0,36.186   c0,4.699,0.892,9.281,2.65,13.615c1.821,4.489,4.495,8.518,7.949,11.971c3.454,3.455,7.481,6.129,11.971,7.949   c4.336,1.76,8.917,2.649,13.617,2.649c4.7,0,9.28-0.892,13.616-2.649c4.488-1.82,8.518-4.494,11.971-7.949   c3.455-3.453,6.129-7.48,7.949-11.971c1.758-4.334,2.648-8.916,2.648-13.615c0-4.7-0.891-9.282-2.648-13.618   c-1.82-4.488-4.496-8.518-7.949-11.971s-7.479-6.129-11.971-7.949C45.467,0.891,40.887,0,36.187,0   C31.487,0,26.906,0.891,22.57,2.648z M9.044,51.419c-1.743-1.094-3.349-2.354-4.771-3.838c-2.172-6.112-2.54-12.729-1.101-19.01   c0.677-1.335,1.447-2.617,2.318-3.845c0.269-0.379,0.518-0.774,0.806-1.142l8.166,4.832c0,0.064,0,0.134,0,0.205   c-0.021,4.392,0.425,8.752,1.313,13.049c0.003,0.02,0.006,0.031,0.01,0.049l-6.333,9.93C9.314,51.579,9.177,51.503,9.044,51.419z    M33.324,68.206c1.409,0.719,2.858,1.326,4.347,1.82c-6.325,0.275-12.713-1.207-18.36-4.447L33,68.018   C33.105,68.085,33.212,68.149,33.324,68.206z M33.274,65.735L17.12,62.856c-1.89-2.295-3.59-4.723-5.051-7.318   c-0.372-0.66-0.787-1.301-1.102-1.99l6.327-9.92c0.14,0.035,0.296,0.072,0.473,0.119c3.958,1.059,7.986,1.812,12.042,2.402   c0.237,0.033,0.435,0.062,0.604,0.08l7.584,13.113c-1.316,1.85-2.647,3.69-4.007,5.51C33.764,65.155,33.524,65.446,33.274,65.735z    M60.15,60.149c-1.286,1.287-2.651,2.447-4.08,3.481c-0.237-1.894-0.646-3.75-1.223-5.563l8.092-15.096   c2.229-1.015,4.379-2.166,6.375-3.593c0.261-0.185,0.478-0.392,0.646-0.618C69.374,46.561,66.104,54.196,60.15,60.149z    M59.791,40.571c0.301,0.574,0.598,1.154,0.896,1.742l-7.816,14.58c-0.045,0.01-0.088,0.02-0.133,0.026   c-4.225,0.789-8.484,1.209-12.779,1.229l-7.8-13.487c1.214-2.254,2.417-4.517,3.61-6.781c0.81-1.536,1.606-3.082,2.401-4.627   l16.143-1.658C56.29,34.495,58.163,37.457,59.791,40.571z M56.516,23.277c-0.766,2.023-1.586,4.025-2.401,6.031l-15.726,1.615   c-0.188-0.248-0.383-0.492-0.588-0.725c-1.857-2.103-3.726-4.193-5.592-6.289c0.017-0.021,0.034-0.037,0.051-0.056   c-0.753-0.752-1.508-1.504-2.261-2.258l4.378-13.181c0.302-0.08,0.606-0.147,0.913-0.18c2.38-0.242,4.763-0.516,7.149-0.654   c1.461-0.082,2.93-0.129,4.416-0.024l10.832,12.209C57.314,20.943,56.95,22.124,56.516,23.277z M60.15,12.221   c2.988,2.99,5.302,6.402,6.938,10.047c-2.024-1.393-4.188-2.539-6.463-3.473c-0.354-0.146-0.717-0.275-1.086-0.402L48.877,6.376   c0.074-0.519,0.113-1.039,0.129-1.563C53.062,6.464,56.864,8.936,60.15,12.221z M25.334,4.182c0.042,0.031,0.062,0.057,0.086,0.064   c2.437,0.842,4.654,2.082,6.744,3.553l-4.09,12.317c-0.021,0.006-0.041,0.012-0.061,0.021c-0.837,0.346-1.69,0.656-2.514,1.031   c-3.395,1.543-6.705,3.252-9.823,5.301l-8.071-4.775c0.012-0.252,0.055-0.508,0.141-0.736c0.542-1.444,1.075-2.896,1.688-4.311   c0.472-1.09,1.01-2.143,1.597-3.172c0.384-0.424,0.782-0.844,1.192-1.254c3.833-3.832,8.363-6.553,13.186-8.162   C25.384,4.098,25.358,4.139,25.334,4.182z"/>
                                </g>
                                </svg>
                                <h2 class="text-black mb-2 font-w600">Total Booking</h2>
                                <h3 class="text-black mb-2 font-w600">12</h3>
                                <p class="mb-0 fs-13">
                                    <svg width="29" height="22" viewbox="0 0 29 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <g filter="url(#filter0_d2)">
                                    <path d="M5 16C5.91797 14.9157 8.89728 11.7277 10.5 10L16.5 13L23.5 4" stroke="#2BC155" stroke-width="2" stroke-linecap="round"></path>
                                    </g>
                                    <defs>
                                <filter id="filter0_d2" x="-3.05176e-05" y="-6.10352e-05" width="28.5001" height="22.0001" filterunits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                    <feflood flood-opacity="0" result="BackgroundImageFix"></feflood>
                                    <fecolormatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"></fecolormatrix>
                                    <feoffset dy="1"></feoffset>
                                    <fegaussianblur stddeviation="2"></fegaussianblur>
                                    <fecolormatrix type="matrix" values="0 0 0 0 0.172549 0 0 0 0 0.72549 0 0 0 0 0.337255 0 0 0 0.61 0"></fecolormatrix>
                                    <feblend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow"></feblend>
                                    <feblend mode="normal" in="SourceGraphic" in2="effect1_dropShadow" result="shape"></feblend>
                                </filter>
                                </defs>
                                </svg>
                                <span class="text-success mr-1">20%</span>This week
                                </p>	
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6">
                        <div class="card card-coin">
                            <div class="card-body text-center">
                                <svg style="height: 80px" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 290 290" style="enable-background:new 0 0 290 290;" xml:space="preserve">
                                <path color-rendering="auto" image-rendering="auto" shape-rendering="auto" color-interpolation="sRGB" d="M45,0  c-2.761,0-5,2.239-5,5v280c0,2.761,2.239,5,5,5h200c2.761,0,5-2.239,5-5V5c0-2.761-2.239-5-5-5L45,0L45,0z M50,10h50v15  c0,2.761,2.239,5,5,5h80c2.761,0,5-2.239,5-5V10h50v129.963h-50.285c-2.5-22.452-21.612-40-44.715-40  c-23.103,0-42.213,17.548-44.713,40H50V10z M110,10h70v10h-70V10z M145,109.963c17.69,0,32.229,12.997,34.643,30h-69.283  C112.773,122.96,127.31,109.963,145,109.963z M50,149.963h50.287c2.5,22.453,21.61,40,44.713,40s42.215-17.547,44.715-40H240V280  h-50v-15c0-2.761-2.239-5-5-5h-80c-2.761,0-5,2.239-5,5v15H50V149.963L50,149.963z M110.359,149.963h69.283  c-2.413,17.003-16.952,30-34.643,30C127.31,179.963,112.773,166.966,110.359,149.963z M110,270h70v10h-70C110,280,110,270,110,270z"/>
                                <g>
                                </svg>
                                <h2 class="text-black mb-2 font-w600">Total Field Has Been Played</h2>
                                <h2 class="text-black mb-2 font-w600">3</h2>
                                <p class="mb-0 fs-14">
                                    <svg width="29" height="22" viewbox="0 0 29 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <g filter="url(#filter0_d4)">
                                    <path d="M5 4C5.91797 5.08433 8.89728 8.27228 10.5 10L16.5 7L23.5 16" stroke="#FF2E2E" stroke-width="2" stroke-linecap="round"></path>
                                    </g>
                                    <defs>
                                <filter id="filter0_d4" x="-3.05176e-05" y="0" width="28.5001" height="22.0001" filterunits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                    <feflood flood-opacity="0" result="BackgroundImageFix"></feflood>
                                    <fecolormatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"></fecolormatrix>
                                    <feoffset dy="1"></feoffset>
                                    <fegaussianblur stddeviation="2"></fegaussianblur>
                                    <fecolormatrix type="matrix" values="0 0 0 0 1 0 0 0 0 0.180392 0 0 0 0 0.180392 0 0 0 0.61 0"></fecolormatrix>
                                    <feblend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow"></feblend>
                                    <feblend mode="normal" in="SourceGraphic" in2="effect1_dropShadow" result="shape"></feblend>
                                </filter>
                                </defs>
                                </svg>
                                <span class="text-danger mr-1">35%</span>This week
                                </p>	
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6">
                        <div class="card card-coin">
                            <div class="card-body text-center">
                                <svg style="height: 80px" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 489.003 489.003" style="enable-background:new 0 0 489.003 489.003;" xml:space="preserve">
                                <g>
                                <path d="M305.802,156.814c-33.8-19.5-88.4-19.5-122,0s-33.4,51.2,0.4,70.7s88.4,19.5,122,0   C339.802,207.914,339.602,176.314,305.802,156.814z M290.302,187.514c-2.9,0.9-5.8,1.9-8.7,2.8c-1.8,0.5-2.9,0.6-3.7,0.2   c-0.4-0.3-0.8-0.7-1.2-1.3c-1.9-3.1-4.8-5.8-8.7-8c-0.5-0.3-1.1-0.6-1.7-0.9c-1.3-0.7-2.5-1.2-4.1-1.6c-5.6-1.3-10.6,0.8-9.9,4.3   c0.3,1.8,1.3,3.4,2.5,5.1c2,2.8,4.2,5.6,5.6,8.6c4.7,9.4-5.5,18.7-21.9,19.6c-5.9,0.3-11.5-0.4-16.7-2.1c-2.3-0.7-4-0.7-5.7,0.5   c-1.7,1.1-3.6,2.1-5.4,3.1c-1.6,0.9-3.3,1-4.9,0.1c-1.2-0.6-2.3-1.3-3.5-1.9c-0.8-0.5-1.6-0.9-2.4-1.4c-1.7-1-1.5-2,0.1-3   c1.3-0.8,2.6-1.5,3.8-2.3c2.9-1.7,2.9-1.8,0.5-3.7c-3-2.4-5.7-4.9-7.4-7.7c-1.3-2.2-1-2.7,2.5-3.9c2.6-0.8,5.1-1.7,7.7-2.5   c1.9-0.6,3-0.7,3.8-0.3c0.5,0.3,0.8,0.7,1.2,1.4c1.7,3.2,4.6,6,8.1,8.7c0.6,0.5,1.3,0.9,1.9,1.3c1.9,1.1,4.1,1.9,6.6,2.5   c6.5,1.5,12.3-1.1,11.7-5.2c-0.2-1.4-0.9-2.6-1.8-3.9c-2.2-3.3-5.1-6.4-6.6-9.9c-2.4-5.6-1.3-10.7,6.4-14.7c8.8-4.5,18.6-4.8,29-2   c4.3,1.2,4.2,1.2,7.5-0.7c1.1-0.7,2.2-1.3,3.3-1.9c2.5-1.4,3.4-1.4,5.9,0c0.8,0.4,1.5,0.9,2.3,1.3c5.3,3,5.3,3,0.1,6.1   c-3.7,2.1-3.7,2.1-0.6,4.6c2.4,1.9,4.3,3.9,5.7,6.1C292.702,186.014,292.302,186.914,290.302,187.514z M350.902,253.314   c-9.3,5.4-24.4,5.4-33.8,0c-9.3-5.4-9.4-14.2-0.1-19.6c9.3-5.4,24.4-5.4,33.8,0C360.202,239.114,360.302,247.914,350.902,253.314z    M172.902,150.514c-9.3,5.4-24.4,5.4-33.8,0c-9.4-5.4-9.4-14.2-0.1-19.6c9.3-5.4,24.4-5.4,33.8,0   C182.102,136.314,182.202,145.114,172.902,150.514z M477.402,211.414l-266.5-153.9c-15.4-8.9-40.4-8.9-55.7,0l-142.9,83   c-15.3,8.9-15.2,23.4,0.2,32.3l266.5,153.9c15.4,8.9,40.4,8.9,55.7,0l142.8-83C492.902,234.814,492.802,220.314,477.402,211.414z    M337.602,297.414c-1.7-1.6-3.7-3.1-6.1-4.5c-15.6-9-40.9-9-56.4,0c-1.2,0.7-2.2,1.4-3.3,2.2l-206.9-119.5c2-0.8,3.9-1.7,5.7-2.7   c15.5-9,15.4-23.6-0.2-32.6c-1.7-1-3.5-1.9-5.4-2.6l85.3-49.6c1.3,1.1,2.8,2.1,4.5,3.1c15.6,9,40.9,9,56.4,0c1.8-1,3.3-2.1,4.7-3.3   l207.6,119.8c-2.8,1-5.3,2.1-7.7,3.5c-15.5,9-15.5,23.7,0.2,32.7c2.4,1.4,5.1,2.5,7.9,3.5L337.602,297.414z M5.502,203.614   c6.8-3.5,17.3-3,23.8,0.8l278.6,160.8l150.6-87.6c6.5-3.8,17.2-3.8,23.8,0c6.6,3.8,6.6,10,0.1,13.8l-160.1,93   c-7.8,4.6-20.6,4.6-28.5,0l-5.6-3.2l-3.9-2.3l-279.3-161.2C-1.798,213.814-1.698,207.314,5.502,203.614z M482.602,341.714   l-160.1,93c-7.8,4.6-20.6,4.6-28.5,0l-5.5-3.2l-4-2.3l-279.3-161.2c-6.8-3.9-6.6-10.4,0.6-14.1c6.7-3.5,17.3-3,23.8,0.8   l278.5,160.8l150.6-87.5c6.6-3.8,17.2-3.8,23.8,0C489.102,331.714,489.202,337.914,482.602,341.714z"/>
                                </g>
                                </svg>
                                <h2 class="text-black mb-2 font-w600">Total Money</h2>
                                <h2 class="text-black mb-2 font-w600">112k</h2>
                                <p class="mb-0 fs-14">
                                    <svg width="29" height="22" viewbox="0 0 29 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <g filter="url(#filter0_d5)">
                                    <path d="M5 16C5.91797 14.9157 8.89728 11.7277 10.5 10L16.5 13L23.5 4" stroke="#2BC155" stroke-width="2" stroke-linecap="round"></path>
                                    </g>
                                    <defs>
                                <filter id="filter0_d5" x="-3.05176e-05" y="-6.10352e-05" width="28.5001" height="22.0001" filterunits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                    <feflood flood-opacity="0" result="BackgroundImageFix"></feflood>
                                    <fecolormatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"></fecolormatrix>
                                    <feoffset dy="1"></feoffset>
                                    <fegaussianblur stddeviation="2"></fegaussianblur>
                                    <fecolormatrix type="matrix" values="0 0 0 0 0.172549 0 0 0 0 0.72549 0 0 0 0 0.337255 0 0 0 0.61 0"></fecolormatrix>
                                    <feblend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow"></feblend>
                                    <feblend mode="normal" in="SourceGraphic" in2="effect1_dropShadow" result="shape"></feblend>
                                </filter>
                                </defs>
                                </svg>
                                <span class="text-success mr-1">45%</span>This week
                                </p>	
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-12 col-xxl-12 mt-2">
                    <div class="card">
                        <div class="card-header pb-0 d-block d-sm-flex flex-wrap border-0 align-items-center">
                            <div class="mr-auto mb-3">
                                <h4 class="fs-20 text-black">Income Overview</h4>
                            </div>
                        </div>
                        <div class="card-body pb-0 pt-sm-3 pt-0">
                            <div id="chartBarRunning" class="bar-chart"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script data-cfasync="false" src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="vendor/global/global.min.js"></script>
        <script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>

        <!-- Apex Chart -->
        <script src="vendor/apexchart/apexchart.js"></script>

        <!-- Dashboard 1 -->
        <script src="js/dashboard/coin-details.js"></script>

        <!-- momment js is must -->
        <script src="vendor/moment/moment.min.js"></script>
        <script src="vendor/bootstrap-daterangepicker/daterangepicker.js"></script>

        <!-- Datatable -->
        <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="js/plugins-init/datatables.init.js"></script>
        <script src="js/custom.min.js"></script>
        <script src="js/deznav-init.js"></script>
        <script src="js/demo.js"></script>
    </body>
</html>
