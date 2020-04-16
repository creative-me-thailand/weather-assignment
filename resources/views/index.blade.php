<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello World</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="{{ asset('portfolio/css/bootstrap.min.css') }}">

    <!-- FontAwesome CSS -->
    <link rel="stylesheet" href="{{ asset('portfolio/css/font-awesome.min.css') }}">

    <!-- Styles -->
    <link rel="stylesheet" href="{{ asset('portfolio/style.css') }}">
</head>
<body>
{{-- <header class="site-header">
    <div class="site-branding">
        <h1 class="site-title"><a href="index.html" rel="home"><img src="{{ asset('portfolio/images/logo.png') }}" alt="Logo"></a></h1>
    </div><!-- .site-branding -->

    <div class="hamburger-menu">
        <div class="menu-icon">
            <img src="{{ asset('portfolio/images/menu-icon.png') }}" alt="menu icon">
        </div><!-- .menu-icon -->

        <div class="menu-close-icon">
            <img src="{{ asset('portfolio/images/x.png') }}" alt="menu close icon">
        </div><!-- .menu-close-icon -->
    </div><!-- .hamburger-menu --> 
</header> --}}

<div class="nav-bar-sep d-lg-none"></div>

<div class="outer-container home-page">
    <div class="container-fluid">
        <div class="row addimage">

        </div><!-- .row -->
        

        <div class="row">
            <div class="col">
                <nav class="post-nav">
                    <ul class="flex justify-content-between align-items-center urlpage">
                        
                        
                    </ul>
                </nav><!-- .post-nav -->
            </div><!-- .col -->
        </div><!-- .row -->
    </div><!-- .container-fluid -->
</div><!-- .outer-container -->

<script type='text/javascript' src='{{ asset('portfolio/js/jquery.js') }}'></script>
<script type='text/javascript' src='{{ asset('portfolio/js/custom.js') }}'></script>

<script>
    (function() {
        var defaultid = "<?php echo $id ?>"
        
        var characterAPI = "https://rickandmortyapi.com/api/character/?page="+defaultid;
        jQuery.getJSON( characterAPI, {
        tags: "character",
        tagmode: "any",
        format: "json"
        })
        .done(function( data ) {
            var item = data.results;
            let beforeurl = parseInt(defaultid) - 1;
            let afterurl = parseInt(defaultid) + 1;
            let beforeupage = '{!! url("/index/'+beforeurl+'") !!}'
            let afterpage = '{!! url("/index/'+afterurl+'") !!}'

            let htmlpage = "";

                if (defaultid != 1) {
                    htmlpage += '<li><a href="'+beforeupage+'"><img src="{{ asset("portfolio/images/angle-left.png") }}" alt="Previous"></a></li>';
                } else {
                    htmlpage += '<li><a href=""><img src="" alt=""></a></li>';
                }

                htmlpage += '<li><a href=""><img src="" alt=""></a></li>';
                htmlpage += '<li><a href="'+afterpage+'"><img src="{{ asset("portfolio/images/angle-right.png") }}" alt="Next"></a></li>'; 
                    
                jQuery('.urlpage').append(htmlpage);
            
            jQuery.each( item, function( key, val ) {
                let url = '{!! url("/detail/'+this.id+'") !!}'
                
                let html = "";
                    html += '<div class="col-12 col-sm-4 col-md-2 no-padding">';
                    html += '<div class="portfolio-content">';
                    html += '<figure> <img src="'+this.image+'" alt=""> </figure>';
                    html += '<div class="entry-content flex flex-column align-items-center justify-content-center">';
                    html += '<h3><a href="'+url+'">'+this.name+'</a></h3>';
                    html += '<ul class="flex flex-wrap justify-content-center">';
                    html += '<li><a href="#">'+this.gender+',</a></li>';
                    html += '<li><a href="#">'+this.status+'</a></li>';
                    html += '</ul>';
                    html += '</div><!-- .entry-content -->';
                    html += '</div><!-- .portfolio-content -->';
                    html += '</div><!-- .col -->';

                    jQuery('.addimage').append(html);
                });
            
        });
    })();
</script>

</body>
</html>