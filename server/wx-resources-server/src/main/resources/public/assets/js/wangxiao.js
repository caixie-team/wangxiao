/*!
 * remark (http://getbootstrapadmin.com/remark)
 * Copyright 2016 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */
(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    // Example NProgress
    // -----------------
    (function() {
      // Start Progress Loader
      // NProgress.start();

      // On click event gather options and Init NProgress Plugin
      $(document).on('click', '.btn', function(e) {
        var $target = $(e.target);
        var id = $target.attr('id');

        switch (id) {
          // Loader Example Increments
          case 'exampleNProgressStart':
            NProgress.start();
            break;
          case 'exampleNProgressSet':
            NProgress.set(0.50);
            break;
          case 'exampleNProgressInc':
            NProgress.inc();
            break;
          case 'exampleNProgressDone':
            NProgress.done(true);
            break;

            // Loader Positions
          case 'exampleNProgressDefault':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressHeader':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-header" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressBottom':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-bottom" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;

            // Loader Contextuals
          case 'exampleNProgressPrimary':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-primary" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressSuccess':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-success" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressInfo':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-info" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressWarning':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-warning" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressDanger':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-danger" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressDark':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-dark" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
          case 'exampleNProgressLight':
            // ReConfigure Progress Loader
            NProgress.done(true);
            NProgress.configure({
              template: '<div class="bar nprogress-bar-light" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
            });
            NProgress.start();
            break;
        }
      });
    })();
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // $('#exampleModal').on('show.bs.modal', function(event) {
  //   var button = $(event.relatedTarget);
  //   var recipient = button.data('whatever');
  //   var modal = $(this);
  //   modal.find('.modal-title').text('New message to ' + recipient);
  //   modal.find('.modal-body input').val(recipient);
  // });

  window.exampleBootboxAlertCallback = function() {
    toastr.info("Hello world callback");
  };

  window.exampleBootboxConfirmCallback = function(result) {
    toastr.info("Confirm result: " + result);
  };

  window.exampleBootboxPromptCallback = function(result) {
    if (result === null) {
      toastr.info("Prompt dismissed");
    } else {
      toastr.info("Hi <b>" + result + "</b>");
    }
  };

  // Example Examples
  // ----------------
  (function() {
    $('#exampleBootboxPromptDefaultValue').on('click', function() {
      bootbox.prompt({
        title: "What is your real name?",
        value: "makeusabrew",
        callback: function(result) {
          if (result === null) {
            toastr.info("Prompt dismissed");
          } else {
            toastr.info("Hi <b>" + result + "</b>");
          }
        }
      });
    });

    $('#exampleBootboxCustomDialog').on('click', function() {
      bootbox.dialog({
        message: "I am a custom dialog",
        title: "Custom title",
        buttons: {
          success: {
            label: "Success!",
            className: "btn-success",
            callback: function() {
              toastr.info("great success");
            }
          },
          danger: {
            label: "Danger!",
            className: "btn-danger",
            callback: function() {
              toastr.info("uh oh, look out!");
            }
          },
          main: {
            label: "Click ME!",
            className: "btn-primary",
            callback: function() {
              toastr.info("Primary button");
            }
          }
        }
      });
    });

    $('#exampleBootboxCustomHtmlContents').on('click', function() {
      bootbox.dialog({
        title: "That html",
        message: 'You can also use <b>html</b>'
      });
    });

    $('#exampleBootboxCustomHtmlForms').on('click', function() {
      bootbox.dialog({
        title: "This is a form in a modal.",
        message: '<form class="form-horizontal">' +
          '<div class="form-group">' +
          '<label class="col-md-4 control-label" for="name">Name</label>' +
          '<div class="col-md-6">' +
          '<input type="text" class="form-control input-md" id="name" name="name" placeholder="Your name"> ' +
          '<span class="help-block">Here goes your name</span></div>' +
          '</div>' +
          '<div class="form-group">' +
          '<label class="col-md-4 control-label" for="awesomeness">How awesome is this?</label>' +
          '<div class="col-md-6"><div class="radio-custom radio-primary">' +
          '<input type="radio" name="awesomeness" id="awesomeness-0" value="Really awesome" checked>' +
          '<label for="awesomeness-0">Really awesome </label>' +
          '</div><div class="radio-custom radio-primary">' +
          '<input type="radio" name="awesomeness" id="awesomeness-1" value="Super awesome">' +
          '<label for="awesomeness-1">Super awesome </label>' +
          '</div>' +
          '</div></div>' +
          '</form>',
        buttons: {
          success: {
            label: "Save",
            className: "btn-success",
            callback: function() {
              var name = $('#name').val();
              var answer = $("input[name='awesomeness']:checked").val();
              toastr.info("Hello " + name + ". You've chosen <b>" + answer + "</b>");
            }
          }
        }
      });
    });
  })();

  // Example Styles
  // --------------
  (function() {
    $('#exampleWarningConfirm').on("click", function() {
      swal({
          title: "Are you sure?",
          text: "You will not be able to recover this imaginary file!",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: '#DD6B55',
          confirmButtonText: 'Yes, delete it!',
          closeOnConfirm: false,
          //closeOnCancel: false
        },
        function() {
          swal("Deleted!", "Your imaginary file has been deleted!", "success");
        });
    });

    $('#exampleWarningCancel').on("click", function() {
      swal({
          title: "Are you sure?",
          text: "You will not be able to recover this imaginary file!",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: '#DD6B55',
          confirmButtonText: 'Yes, delete it!',
          cancelButtonText: "No, cancel plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            swal("Deleted!", "Your imaginary file has been deleted!", "success");
          } else {
            swal("Cancelled", "Your imaginary file is safe :)", "error");
          }
        });
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Demo 2
  // ------
  (function() {
    $('#exampleContext').contextmenu({
      target: '#exampleContextMenu',
      before: function(e) {
        // This function is optional.
        // Here we use it to stop the event if the user clicks a span
        e.preventDefault();
        if (e.target.tagName == 'SPAN') {
          e.preventDefault();
          this.closemenu();
          return false;
        }
        this.getMenu().find("li").eq(4).find('a').html("This was dynamically changed");
        return true;
      }
    });
  })();


  // Demo 3
  // ------
  (function() {
    $('#exampleContext2').contextmenu({
      target: '#exampleContextMenu',
      onItem: function(context, e) {
        alert($.trim($(e.target).text()));
      }
    });

    $('#exampleContextMenu').on('show.bs.context', function(e) {
      console.log('before show event');
    });

    $('#exampleContextMenu').on('shown.bs.context', function(e) {
      console.log('after show event');
    });

    $('#exampleContextMenu').on('hide.bs.context', function(e) {
      console.log('before hide event');
    });

    $('#exampleContextMenu').on('hidden.bs.context', function(e) {
      console.log('after hide event');
    });
  })();


})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  hljs.initHighlightingOnLoad();


})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;


  $(document).ready(function($) {
    Site.run();
  });


  // Example Popup Zoom Gallery
  // --------------------------
  $('#exampleZoomGallery').magnificPopup({
    delegate: 'a',
    type: 'image',
    closeOnContentClick: false,
    closeBtnInside: false,
    mainClass: 'mfp-with-zoom mfp-img-mobile',
    image: {
      verticalFit: true,
      titleSrc: function(item) {
        return item.el.attr('title') + ' &middot; <a class="image-source-link" href="' + item.el.attr('data-source') + '" target="_blank">image source</a>';
      }
    },
    gallery: {
      enabled: true
    },
    zoom: {
      enabled: true,
      duration: 300, // don't foget to change the duration also in CSS
      opener: function(element) {
        return element.find('img');
      }
    }
  });


  // Example Popup Gallery
  // ---------------------
  $('#exampleGallery').magnificPopup({
    delegate: 'a',
    type: 'image',
    tLoading: 'Loading image #%curr%...',
    mainClass: 'mfp-img-mobile',
    gallery: {
      enabled: true,
      navigateByImgClick: true,
      preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
    },
    image: {
      tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
      titleSrc: function(item) {
        return item.el.attr('title') + '<small>by amazingSurge</small>';
      }
    }
  });


  // Example Popup With Css Animation
  // --------------------------------
  $('.popup-with-css-anim').magnificPopup({
    type: 'image',
    removalDelay: 500,
    preloader: true,
    callbacks: {
      beforeOpen: function() {
        this.st.image.markup = this.st.image.markup.replace('mfp-figure', 'mfp-figure mfp-with-anim');
        this.st.mainClass = this.st.el.attr('data-effect');
      }
    },
    closeOnContentClick: true,
    midClick: true
  });


  // Example Popup With Video Rr Map
  // -------------------------------
  $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
    disableOn: 700,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,

    fixedContentPos: false
  });


  // Example Popup With Video Rr Map
  // -------------------------------
  $('#examplePopupForm').magnificPopup({
    type: 'inline',
    preloader: false,
    focus: '#inputName',

    // When elemened is focused, some mobile browsers in some cases zoom in
    // It looks not nice, so we disable it:
    callbacks: {
      beforeOpen: function() {
        if ($(window).width() < 700) {
          this.st.focus = false;
        } else {
          this.st.focus = '#inputName';
        }
      }
    }
  });


  // Example Ajax Popup
  // ------------------
  $('#examplePopupAjaxAlignTop').magnificPopup({
    type: 'ajax',
    alignTop: true,
    overflowY: 'scroll' // as we know that popup content is tall we set scroll overflow by default to avoid jump
  });

  $('#examplePopupAjax').magnificPopup({
    type: 'ajax'
  });


  // Example Popup Modal
  // -------------------
  $('.popup-modal').magnificPopup({
    type: 'inline',
    preloader: false,
    modal: true
  });

  $(document).on('click', '.popup-modal-dismiss', function(e) {
    e.preventDefault();
    $.magnificPopup.close();
  });


  // Example Error Handling
  // ----------------------
  $('#exampleBrokenImage, #exampleBrokenAjax').magnificPopup({});

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;

  $(document).ready(function() {
    Site.run();

    // Simple
    // ------------------
    (function() {
      var simpleMap = new GMaps({
        el: '#simpleGmap',
        zoom: 8,
        center: {
          lat: -34.397,
          lng: 150.644
        }
      });
    })();

    // Custom
    // ------------------
    (function() {
      var map = new GMaps({
        el: '#customGmap',
        lat: -12.043333,
        lng: -77.028333,
        zoomControl: true,
        zoomControlOpt: {
          style: "SMALL",
          position: "TOP_LEFT"
        },
        panControl: true,
        streetViewControl: false,
        mapTypeControl: false,
        overviewMapControl: false

      });

      map.drawOverlay({
        lat: -12.043333,
        lng: -77.028333,
        content: '<i class="md-pin" style="font-size:40px;color:' + $.colors("red", 500) + ';"></i>',
      });

      map.drawOverlay({
        lat: -12.05449279282314,
        lng: -77.04333,
        content: '<i class="md-pin" style="font-size:32px;color:' + $.colors("primary", 500) + ';"></i>',
      });

      map.addStyle({
        styledMapName: "Styled Map",
        styles: $.components.get('gmaps', 'styles'),
        mapTypeId: "map_style"
      });

      map.setStyle("map_style");

      var path = [
        [-12.044012922866312, -77.02470665341184],
        [-12.05449279282314, -77.03024273281858],
        [-12.055122327623378, -77.03039293652341],
        [-12.075917129727586, -77.02764635449216],
        [-12.07635776902266, -77.02792530422971],
        [-12.076819390363665, -77.02893381481931],
        [-12.088527520066453, -77.0241058385925],
        [-12.090814532191756, -77.02271108990476]
      ];

      map.drawPolyline({
        path: path,
        strokeColor: '#131540',
        strokeOpacity: 0.6,
        strokeWeight: 6
      });
    })();

    // Markers
    // ------------------
    (function() {
      var map = new GMaps({
        div: '#markersGmap',
        lat: -12.043333,
        lng: -77.028333
      });
      map.addMarker({
        lat: -12.043333,
        lng: -77.03,
        title: 'Lima',
        details: {
          database_id: 42,
          author: 'HPNeo'
        },
        click: function(e) {
          if (console.log)
            console.log(e);
          alert('You clicked in this marker');
        }
      });
      map.addMarker({
        lat: -12.042,
        lng: -77.028333,
        title: 'Marker with InfoWindow',
        infoWindow: {
          content: '<p>You clicked in this marker</p>'
        }
      });
    })();

    // Polylines
    // ------------------
    (function() {
      var map = new GMaps({
        div: '#polylinesGmap',
        lat: -12.043333,
        lng: -77.028333,
        click: function(e) {
          console.log(e);
        }
      });

      var path = [
        [-12.044012922866312, -77.02470665341184],
        [-12.05449279282314, -77.03024273281858],
        [-12.055122327623378, -77.03039293652341],
        [-12.075917129727586, -77.02764635449216],
        [-12.07635776902266, -77.02792530422971],
        [-12.076819390363665, -77.02893381481931],
        [-12.088527520066453, -77.0241058385925],
        [-12.090814532191756, -77.02271108990476]
      ];

      map.drawPolyline({
        path: path,
        strokeColor: '#131540',
        strokeOpacity: 0.6,
        strokeWeight: 6
      });
    })();

    // Polygons
    // ------------------
    (function() {
      var map = new GMaps({
        div: '#polygonsGmap',
        lat: -12.043333,
        lng: -77.028333
      });

      var path = [
        [-12.040397656836609, -77.03373871559225],
        [-12.040248585302038, -77.03993927003302],
        [-12.050047116528843, -77.02448169303511],
        [-12.044804866577001, -77.02154422636042]
      ];

      map.drawPolygon({
        paths: path,
        strokeColor: '#BBD8E9',
        strokeOpacity: 1,
        strokeWeight: 3,
        fillColor: '#BBD8E9',
        fillOpacity: 0.6
      });
    })();

    // Fusion Tables layers
    // ------------------
    (function() {
      var infoWindow = new google.maps.InfoWindow({}),
        map = new GMaps({
          div: '#FTLGmap',
          zoom: 11,
          lat: 41.850033,
          lng: -87.6500523
        });

      map.loadFromFusionTables({
        query: {
          select: '\'Geocodable address\'',
          from: '1mZ53Z70NsChnBMm-qEYmSDOvLXgrreLTkQUvvg'
        },
        suppressInfoWindows: true,
        events: {
          click: function(point) {
            infoWindow.setContent('You clicked here!');
            infoWindow.setPosition(point.latLng);
            infoWindow.open(map.map);
          }
        }
      });
    })();

    // Panoramas
    // ------------------
    (function() {
      var panorama = GMaps.createPanorama({
        el: '#panoramasGmap',
        lat: 42.3455,
        lng: -71.0983
      });
    })();

    // Satellite
    // ------------------
    (function() {
      var simpleMap = new GMaps({
        div: "#satelliteGmap",
        lat: 0,
        lng: 0,
        zoom: 1,
        scrollwheel: !1
      }).setMapTypeId(google.maps.MapTypeId.SATELLITE);
    })();

  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Example Scrollable Dynamic Content
  // ----------------------------------
  (function() {
    $('#btnExampleDynamicAppend').on('click', function() {
      $('#exampleDynamicContent').append("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero.</p>" +
        "<p>Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh.</p>" +
        "<p>Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh.</p>" +
        "<p>Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh.</p>" +
        "<p>Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh.</p>" +
        "<p>Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices.</p>"
      );

      $('#exampleDynamic').asScrollable("update");
    });
  })();


  // Example Scrollable Api Access
  // -----------------------------
  (function() {
    // Scroll To Api
    $('.api-scroll-to').on('click', function() {
      var to = $(this).data('to');

      $('#exampleScollableApi').asScrollable('scrollTo', 'vertical', to);
      $('#exampleScollableApi').asScrollable('scrollTo', 'horizontal', to);
    });

    // Scroll By Api
    $('.api-scroll-by').on('click', function() {
      var to = $(this).data('by');
      $('#exampleScollableApi').asScrollable('scrollBy', 'horizontal', to);
      $('#exampleScollableApi').asScrollable('scrollBy', 'vertical', to);
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  window.getExampleTreeview = function() {
    return [{
      text: 'Parent 1',
      href: '#parent1',
      tags: ['4'],
      nodes: [{
        text: 'Child 1',
        href: '#child1',
        tags: ['2'],
        nodes: [{
          text: 'Grandchild 1',
          href: '#grandchild1',
          tags: ['0']
        }, {
          text: 'Grandchild 2',
          href: '#grandchild2',
          tags: ['0']
        }]
      }, {
        text: 'Child 2',
        href: '#child2',
        tags: ['0']
      }]
    }, {
      text: 'Parent 2',
      href: '#parent2',
      tags: ['0']
    }, {
      text: 'Parent 3',
      href: '#parent3',
      tags: ['0']
    }, {
      text: 'Parent 4',
      href: '#parent4',
      tags: ['0']
    }, {
      text: 'Parent 5',
      href: '#parent5',
      tags: ['0']
    }];
  };

  var defaults = $.components.getDefaults("treeview");

  // Example TreeView Json Data
  // --------------------------
  (function() {
    var json = '[' +
      '{' +
      '"text": "Parent 1",' +
      '"nodes": [' +
      '{' +
      '"text": "Child 1",' +
      '"nodes": [' +
      '{' +
      '"text": "Grandchild 1"' +
      '},' +
      '{' +
      '"text": "Grandchild 2"' +
      '}' +
      ']' +
      '},' +
      '{' +
      '"text": "Child 2"' +
      '}' +
      ']' +
      '},' +
      '{' +
      '"text": "Parent 2"' +
      '},' +
      '{' +
      '"text": "Parent 3"' +
      '},' +
      '{' +
      '"text": "Parent 4"' +
      '},' +
      '{' +
      '"text": "Parent 5"' +
      '}' +
      ']';

    var json_options = $.extend({}, defaults, {
      data: json
    });

    $('#exampleJsonData').treeview(json_options);
  })();

  // Example TreeView Searchable
  // ---------------------------
  (function() {
    var options = $.extend({}, defaults, {
      data: getExampleTreeview()
    });

    var $searchableTree = $('#exampleSearchableTree').treeview(options);

    $('#inputSearchable').on('keyup', function(e) {
      var pattern = $(e.target).val();

      var results = $searchableTree.treeview('search', [pattern, {
        'ignoreCase': true,
        'exactMatch': false
      }]);
    });
  })();


  // Example TreeView Expandible
  // ---------------------------
  (function() {
    var options = $.extend({}, defaults, {
      data: getExampleTreeview()
    });

    // Expandible
    var $expandibleTree = $('#exampleExpandibleTree').treeview(options);

    // Expand/collapse all
    $('#exampleExpandAll').on('click', function(e) {
      $expandibleTree.treeview('expandAll', {
        levels: '99'
      });
    });

    $('#exampleCollapseAll').on('click', function(e) {
      $expandibleTree.treeview('collapseAll');
    });
  })();

  // Example TreeView Events
  // -----------------------
  (function() {
    // Events
    var events_toastr = function(msg) {
      toastr.info(msg, '', {
        iconClass: 'toast-just-text toast-info',
        positionClass: 'toast-bottom-right',
        containertId: 'toast-bottom-right'
      });
    };

    var options = $.extend({}, defaults, {
      data: getExampleTreeview(),
      onNodeCollapsed: function(event, node) {
        events_toastr(node.text + ' was collapsed');
      },
      onNodeExpanded: function(event, node) {
        events_toastr(node.text + ' was expanded');
      },
      onNodeSelected: function(event, node) {
        events_toastr(node.text + ' was selected');
      },
      onNodeUnselected: function(event, node) {
        events_toastr(node.text + ' was unselected');
      }
    });

    $('#exampleEvents').treeview(options);
  })();
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example C3 Simple Line
  // ----------------------
  (function() {
    var simple_line_chart = c3.generate({
      bindto: '#exampleC3SimpleLine',
      data: {
        columns: [
          ['data1', 100, 165, 140, 270, 200, 140, 220],
          ['data2', 110, 80, 100, 85, 125, 90, 100]
        ]
      },
      color: {
        pattern: [$.colors("primary", 600), $.colors("green", 600)]
      },
      axis: {
        x: {
          tick: {
            outer: false
          }
        },
        y: {
          max: 300,
          min: 0,
          tick: {
            outer: false,
            count: 7,
            values: [0, 50, 100, 150, 200, 250, 300]
          }
        }
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });
  })();


  // Example C3 Line Regions
  // -----------------------
  (function() {
    var line_regions_chart = c3.generate({
      bindto: '#exampleC3LineRegions',
      data: {
        columns: [
          ['data1', 100, 165, 140, 270, 200, 140, 220],
          ['data2', 110, 80, 100, 85, 125, 90, 100]
        ],
        regions: {
          'data1': [{
            'start': 1,
            'end': 2,
            'style': 'dashed'
          }, {
            'start': 3
          }], // currently 'dashed' style only
          'data2': [{
            'end': 3
          }]
        }
      },
      color: {
        pattern: [$.colors("primary", 600), $.colors("green", 600)]
      },
      axis: {
        x: {
          tick: {
            outer: false
          }
        },
        y: {
          max: 300,
          min: 0,
          tick: {
            outer: false,
            count: 7,
            values: [0, 50, 100, 150, 200, 250, 300]
          }
        }
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });
  })();


  // Example C3 Timeseries
  // ---------------------
  (function() {
    var time_series_chart = c3.generate({
      bindto: '#exampleC3TimeSeries',
      data: {
        x: 'x',
        columns: [
          ['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06'],
          ['data1', 80, 125, 100, 220, 80, 160],
          ['data2', 40, 85, 45, 155, 50, 65]
        ]
      },
      color: {
        pattern: [$.colors("primary", 600), $.colors("green", 600), $.colors("red", 500)]
      },
      padding: {
        right: 40
      },
      axis: {
        x: {
          type: 'timeseries',
          tick: {
            outer: false,
            format: '%Y-%m-%d'
          }
        },
        y: {
          max: 300,
          min: 0,
          tick: {
            outer: false,
            count: 7,
            values: [0, 50, 100, 150, 200, 250, 300]
          }
        }
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });

    setTimeout(function() {
      time_series_chart.load({
        columns: [
          ['data3', 210, 180, 260, 290, 250, 240]
        ]
      });
    }, 1000);
  })();


  // Example C3 Spline
  // -----------------
  (function() {
    var spline_chart = c3.generate({
      bindto: '#exampleC3Spline',
      data: {
        columns: [
          ['data1', 100, 165, 140, 270, 200, 140, 220],
          ['data2', 110, 80, 100, 85, 125, 90, 100]
        ],
        type: 'spline'
      },
      color: {
        pattern: [$.colors("primary", 600), $.colors("green", 600)]
      },
      axis: {
        x: {
          tick: {
            outer: false
          }
        },
        y: {
          max: 300,
          min: 0,
          tick: {
            outer: false,
            count: 7,
            values: [0, 50, 100, 150, 200, 250, 300]
          }
        }
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });
  })();


  // Example C3 Scatter
  // ------------------
  (function() {
    var scatter_chart = c3.generate({
      bindto: '#exampleC3Scatter',
      data: {
        xs: {
          setosa: 'setosa_x',
          versicolor: 'versicolor_x',
        },
        columns: [
          ["setosa_x", 3.5, 3.0, 3.2, 3.1, 3.6, 3.9, 3.4, 3.4, 2.9, 3.1, 3.7, 3.4, 3.0, 3.0, 4.0, 4.2, 3.9, 3.5, 3.8, 3.8, 3.4, 3.7, 3.6, 3.3, 3.4, 3.0, 3.4, 3.5, 3.4, 3.2, 3.1, 3.4, 4.1, 4.2, 3.1, 3.2, 3.5, 3.6, 3.0, 3.4, 3.5, 2.3, 3.2, 3.5, 3.8, 3.0, 3.8, 3.2, 3.7, 3.3],
          ["versicolor_x", 3.2, 3.2, 3.1, 2.3, 2.8, 2.8, 3.3, 2.4, 2.9, 2.7, 2.0, 3.0, 2.2, 2.9, 2.9, 3.1, 3.0, 2.7, 2.2, 2.5, 3.2, 2.8, 2.5, 2.8, 2.9, 3.0, 2.8, 3.0, 2.9, 2.6, 2.4, 2.4, 2.7, 2.7, 3.0, 3.4, 3.1, 2.3, 3.0, 2.5, 2.6, 3.0, 2.6, 2.3, 2.7, 3.0, 2.9, 2.9, 2.5, 2.8],
          ["setosa", 0.2, 0.2, 0.2, 0.2, 0.2, 0.4, 0.3, 0.2, 0.2, 0.1, 0.2, 0.2, 0.1, 0.1, 0.2, 0.4, 0.4, 0.3, 0.3, 0.3, 0.2, 0.4, 0.2, 0.5, 0.2, 0.2, 0.4, 0.2, 0.2, 0.2, 0.2, 0.4, 0.1, 0.2, 0.2, 0.2, 0.2, 0.1, 0.2, 0.2, 0.3, 0.3, 0.2, 0.6, 0.4, 0.3, 0.2, 0.2, 0.2, 0.2],
          ["versicolor", 1.4, 1.5, 1.5, 1.3, 1.5, 1.3, 1.6, 1.0, 1.3, 1.4, 1.0, 1.5, 1.0, 1.4, 1.3, 1.4, 1.5, 1.0, 1.5, 1.1, 1.6, 1.3, 1.5, 1.2, 1.3, 1.4, 1.4, 1.2, 1.5, 1.0, 1.1, 1.0, 1.2, 1.6, 1.5, 1.6, 1.5, 1.3, 1.3, 1.3, 1.2, 1.4, 1.2, 1.0, 1.3, 1.2, 1.3, 1.3, 1.1, 1.3],
        ],
        type: 'scatter'
      },
      color: {
        pattern: [$.colors("green", 600), $.colors("red", 500)]
      },
      axis: {
        x: {
          label: 'Sepal.Width',
          tick: {
            outer: false,
            fit: false
          }
        },
        size: {
          height: 400
        },
        padding: {
          right: 40
        },
        y: {
          label: 'Petal.Width',
          tick: {
            outer: false,
            count: 5,
            values: [0, 0.4, 0.8, 1.2, 1.6]
          }
        }
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });
  })();


  // Example C3 Bar
  // --------------
  (function() {
    var bar_chart = c3.generate({
      bindto: '#exampleC3Bar',
      data: {
        columns: [
          ['data1', 30, 200, 100, 400, 150, 250],
          ['data2', 130, 100, 140, 200, 150, 50]
        ],
        type: 'bar'
      },
      bar: {
        // width: {
        //  ratio: 0.55 // this makes bar width 55% of length between ticks
        // }
        width: {
          max: 20
        }
      },
      color: {
        pattern: [$.colors("red", 400), $.colors("grey", 500), $.colors("grey", 300)]
      },
      grid: {
        y: {
          show: true
        },
        x: {
          show: false
        }
      }
    });

    setTimeout(function() {
      bar_chart.load({
        columns: [
          ['data3', 130, -150, 200, 300, -200, 100]
        ]
      });
    }, 1000);
  })();


  // Example C3 Stacked Bar
  // ----------------------
  (function() {
    var stacked_bar_chart = c3.generate({
      bindto: '#exampleC3StackedBar',
      data: {
        columns: [
          ['data1', -30, 200, 300, 400, -150, 250],
          ['data2', 130, 100, -400, 100, -150, 50],
          ['data3', -230, 200, 200, -300, 250, 250]
        ],
        type: 'bar',
        groups: [
          ['data1', 'data2']
        ]
      },
      color: {
        pattern: [$.colors("primary", 500), $.colors("grey", 400), $.colors("purple", 500), $.colors("light-green", 500)]
      },
      bar: {
        width: {
          max: 45
        }
      },
      grid: {
        y: {
          show: true,
          lines: [{
            value: 0
          }]
        }
      }
    });

    setTimeout(function() {
      stacked_bar_chart.groups([
        ['data1', 'data2', 'data3']
      ]);
    }, 1000);

    setTimeout(function() {
      stacked_bar_chart.load({
        columns: [
          ['data4', 100, -250, 150, 200, -300, -100]
        ]
      });
    }, 1500);

    setTimeout(function() {
      stacked_bar_chart.groups([
        ['data1', 'data2', 'data3', 'data4']
      ]);
    }, 2000);
  })();


  // Example C3 Combination
  // ----------------------
  (function() {
    var combination_chart = c3.generate({
      bindto: '#exampleC3Combination',
      data: {
        columns: [
          ['data1', 30, 20, 50, 40, 60, 50],
          ['data2', 200, 130, 90, 240, 130, 220],
          ['data3', 300, 200, 160, 400, 250, 250],
          ['data4', 200, 130, 90, 240, 130, 220],
          ['data5', 130, 120, 150, 140, 160, 150],
          ['data6', 90, 70, 20, 50, 60, 120],
        ],
        type: 'bar',
        types: {
          data3: 'spline',
          data4: 'line',
          data6: 'area',
        },
        groups: [
          ['data1', 'data2']
        ]
      },
      color: {
        pattern: [$.colors("grey", 500), $.colors("grey", 300), $.colors("yellow", 600), $.colors("primary", 600), $.colors("red", 400), $.colors("green", 600)]
      },
      grid: {
        x: {
          show: false
        },
        y: {
          show: true
        }
      }
    });
  })();


  // Example C3 Pie
  // --------------
  (function() {
    var pie_chart = c3.generate({
      bindto: '#exampleC3Pie',
      data: {
        // iris data from R
        columns: [
          ['data1', 100],
          ['data2', 40],
        ],
        type: 'pie',
      },
      color: {
        pattern: [$.colors("primary", 500), $.colors("grey", 300)]
      },
      legend: {
        position: 'right'
      },
      pie: {
        label: {
          show: false
        },
        onclick: function(d, i) {},
        onmouseover: function(d, i) {},
        onmouseout: function(d, i) {}
      }
    });
  })();


  // Example C3 Donut
  // ----------------
  (function() {
    var donut_chart = c3.generate({
      bindto: '#exampleC3Donut',
      data: {
        columns: [
          ['data1', 120],
          ['data2', 40],
          ['data3', 80]
        ],
        type: 'donut'
      },
      color: {
        pattern: [$.colors("primary", 500), $.colors("grey", 300), $.colors("red", 400)]
      },
      legend: {
        position: 'right'
      },
      donut: {
        label: {
          show: false
        },
        width: 10,
        title: "C3 Dount Chart",
        onclick: function(d, i) {},
        onmouseover: function(d, i) {},
        onmouseout: function(d, i) {}
      }
    });
  })();

  // Example Sub Chart
  // ----------------
  (function() {
    var donut_chart = c3.generate({
      bindto: '#exampleC3Subchart',
      data: {
        columns: [
          ['data1', 100, 165, 140, 270, 200, 140, 220, 210, 190, 100, 170, 250],
          ['data2', 110, 80, 100, 85, 125, 90, 100, 130, 120, 90, 100, 115]
        ],
        type: 'spline'
      },
      color: {
        pattern: [$.colors("primary", 600), $.colors("green", 600)]
      },
      subchart: {
        show: true
      }
    });
  })();

  // Example C3 Zoom
  // ----------------
  (function() {
    var donut_chart = c3.generate({
      bindto: '#exampleC3Zoom',
      data: {
        columns: [
          ['sample', 30, 200, 100, 400, 150, 250, 150, 200, 170, 240, 350, 150, 100, 400, 150, 250, 150, 200, 170, 240, 100, 150, 250, 150, 200, 170, 240, 30, 200, 100, 400, 150, 250, 150, 200, 170, 240, 350, 150, 100, 400, 350, 220, 250, 300, 270, 140, 150, 90, 150, 50, 120, 70, 40]
        ],
        colors: {
          sample: $.colors("primary", 600)
        }
      },
      zoom: {
        enabled: true
      }
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Example Chartist Css Animation
  // ------------------------------
  (function() {
    var cssAnimationData = {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      series: [
        [1, 2, 2.7, 0, 3, 5, 3, 4, 8, 10, 12, 7],
        [0, 1.2, 2, 7, 2.5, 9, 5, 8, 9, 11, 14, 4],
        [10, 9, 8, 6.5, 6.8, 6, 5.4, 5.3, 4.5, 4.4, 3, 2.8]
      ]
    };

    var cssAnimationResponsiveOptions = [
      [
        // Foundation.media_queries.small,
        {
          axisX: {
            labelInterpolationFnc: function(value, index) {
              // Interpolation function causes only every 2nd label to be displayed
              if (index % 2 !== 0) {
                return false;
              } else {
                return value;
              }
            }
          }
        }
      ]
    ];

    new Chartist.Line('#exampleLineAnimation', cssAnimationData, null, cssAnimationResponsiveOptions);
  })();


  // Example Chartist Simple Line
  // ----------------------------
  (function() {
    new Chartist.Line('#exampleSimpleLine', {
      labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
      series: [
        [12, 9, 7, 8, 5],
        [2, 1, 3.5, 7, 3],
        [1, 3, 4, 5, 6]
      ]
    }, {
      fullWidth: true,
      chartPadding: {
        right: 40
      }
    });
  })();


  // Example Chartist Line Scatter
  // -----------------------------
  (function() {
    var ctScatterTimes = function(n) {
      return Array.apply(null, new Array(n));
    };

    var ctScatterData = ctScatterTimes(52).map(Math.random).reduce(function(data, rnd, index) {
      data.labels.push(index + 1);
      data.series.forEach(function(series) {
        series.push(Math.random() * 100);
      });

      return data;
    }, {
      labels: [],
      series: ctScatterTimes(4).map(function() {
        return [];
      })
    });

    var ctScatterOptions = {
      showLine: false,
      axisX: {
        labelInterpolationFnc: function(value, index) {
          return index % 13 === 0 ? 'W' + value : null;
        }
      }
    };

    var ctScatterResponsiveOptions = [
      ['screen and (min-width: 640px)', {
        axisX: {
          labelInterpolationFnc: function(value, index) {
            return index % 4 === 0 ? 'W' + value : null;
          }
        }
      }]
    ];
    new Chartist.Line('#exampleLineScatter', ctScatterData, ctScatterOptions, ctScatterResponsiveOptions);

  })();

  // Example Chartist Line Chart With Tooltips
  // -----------------------------------------
  (function() {
    new Chartist.Line('#exampleTooltipsLine', {
      labels: ['1', '2', '3', '4', '5', '6'],
      series: [{
        name: 'Fibonacci sequence',
        data: [1, 2, 3, 5, 8, 13]
      }, {
        name: 'Golden section',
        data: [1, 1.618, 2.618, 4.236, 6.854, 11.09]
      }]
    });

    var $ctTooltipsChart = $('#exampleTooltipsLine');

    var $ctTooltip = $ctTooltipsChart
      .append('<div class="ct-tooltip"></div>')
      .find('#exampletooltip')
      .hide();

    $ctTooltipsChart.on('mouseenter', '#examplepoint', function() {
      var $point = $(this),
        value = $point.attr('ct:value'),
        seriesName = $point.parent().attr('ct:series-name');
      $ctTooltip.html(seriesName + '<br>' + value).show();
    });

    $ctTooltipsChart.on('mouseleave', '#examplepoint', function() {
      $ctTooltip.hide();
    });

    $ctTooltipsChart.on('mousemove', function(event) {
      $ctTooltip.css({
        left: (event.offsetX || event.originalEvent.layerX) - $ctTooltip.width() / 2 - 10,
        top: (event.offsetY || event.originalEvent.layerY) - $ctTooltip.height() - 40
      });
    });
  })();

  // Example Chartist Line Chart With Area
  // -------------------------------------
  (function() {
    new Chartist.Line('#exampleAreaLine', {
      labels: [1, 2, 3, 4, 5, 6, 7, 8],
      series: [
        [5, 9, 7, 8, 5, 3, 5, 4]
      ]
    }, {
      low: 0,
      showArea: true
    });
  })();

  // Example Chartist Bi-Polar Line
  // ------------------------------
  (function() {
    new Chartist.Line('#exampleOnlyArea', {
      labels: [1, 2, 3, 4, 5, 6, 7, 8],
      series: [
        [1, 2, 3, 1, -2, 0, 1, 0],
        [-2, -1, -2, -1, -2.5, -1, -2, -1],
        [0, 0, 0, 1, 2, 2.5, 2, 1],
        [2.5, 2, 1, 0.5, 1, 0.5, -1, -2.5]
      ]
    }, {
      high: 3,
      low: -3,
      showArea: true,
      showLine: false,
      showPoint: false,
      fullWidth: true,
      axisX: {
        showLabel: false,
        showGrid: false
      }
    });
  })();

  // Example Chartist Advanced Smil Animations
  // -----------------------------------------
  (function() {
    var animationsChart = new Chartist.Line('#exampleLineAnimations', {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
      series: [
        [12, 9, 7, 8, 5, 4, 6, 2, 3, 3, 4, 6],
        [4, 5, 3, 7, 3, 5, 5, 3, 4, 4, 5, 5],
        [5, 3, 4, 5, 6, 3, 3, 4, 5, 6, 3, 4],
        [3, 4, 5, 6, 7, 6, 4, 5, 6, 7, 6, 3]
      ]
    }, {
      low: 0
    });

    // // Let's put a sequence number aside so we can use it in the event callbacks
    var seq = 0,
      delays = 80,
      durations = 500;

    // Once the chart is fully created we reset the sequence
    animationsChart.on('created', function() {
      seq = 0;
    });

    // // On each drawn element by Chartist we use the Chartist.Svg API to trigger SMIL animations
    animationsChart.on('draw', function(data) {
      seq++;

      if (data.type === 'line') {
        // If the drawn element is a line we do a simple opacity fade in. This could also be achieved using CSS3 animations.
        data.element.animate({
          opacity: {
            // The delay when we like to start the animation
            begin: seq * delays + 1000,
            // Duration of the animation
            dur: durations,
            // The value where the animation should start
            from: 0,
            // The value where it should end
            to: 1
          }
        });
      } else if (data.type === 'label' && data.axis === 'x') {
        data.element.animate({
          y: {
            begin: seq * delays,
            dur: durations,
            from: data.y + 100,
            to: data.y,
            // We can specify an easing function from Chartist.Svg.Easing
            easing: 'easeOutQuart'
          }
        });
      } else if (data.type === 'label' && data.axis === 'y') {
        data.element.animate({
          x: {
            begin: seq * delays,
            dur: durations,
            from: data.x - 100,
            to: data.x,
            easing: 'easeOutQuart'
          }
        });
      } else if (data.type === 'point') {
        data.element.animate({
          x1: {
            begin: seq * delays,
            dur: durations,
            from: data.x - 10,
            to: data.x,
            easing: 'easeOutQuart'
          },
          x2: {
            begin: seq * delays,
            dur: durations,
            from: data.x - 10,
            to: data.x,
            easing: 'easeOutQuart'
          },
          opacity: {
            begin: seq * delays,
            dur: durations,
            from: 0,
            to: 1,
            easing: 'easeOutQuart'
          }
        });
      } else if (data.type === 'grid') {
        // Using data.axis we get x or y which we can use to construct our animation definition objects
        var pos1Animation = {
          begin: seq * delays,
          dur: durations,
          from: data[data.axis.units.pos + '1'] - 30,
          to: data[data.axis.units.pos + '1'],
          easing: 'easeOutQuart'
        };

        var pos2Animation = {
          begin: seq * delays,
          dur: durations,
          from: data[data.axis.units.pos + '2'] - 100,
          to: data[data.axis.units.pos + '2'],
          easing: 'easeOutQuart'
        };

        var ctAnimations = {};
        ctAnimations[data.axis.units.pos + '1'] = pos1Animation;
        ctAnimations[data.axis.units.pos + '2'] = pos2Animation;
        ctAnimations.opacity = {
          begin: seq * delays,
          dur: durations,
          from: 0,
          to: 1,
          easing: 'easeOutQuart'
        };

        data.element.animate(ctAnimations);
      }
    });

    // For the sake of the example we update the chart every time it's created with a delay of 10 seconds
    animationsChart.on('created', function() {
      if (window.__exampleAnimateTimeout) {
        clearTimeout(window.__exampleAnimateTimeout);
        window.__exampleAnimateTimeout = null;
      }
      window.__exampleAnimateTimeout = setTimeout(animationsChart.update.bind(animationsChart), 12000);
    });
  })();

  // Example Chartist Svg Path Animation
  // -----------------------------------
  (function() {
    //ct-path-animation
    var pathAnimationChart = new Chartist.Line('#examplePathAnimation', {
      labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
      series: [
        [1, 5, 2, 5, 4, 3],
        [2, 3, 4, 8, 1, 2],
        [5, 4, 3, 2, 1, 0.5]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      fullWidth: true
    });

    pathAnimationChart.on('draw', function(data) {
      if (data.type === 'line' || data.type === 'area') {
        data.element.animate({
          d: {
            begin: 2000 * data.index,
            dur: 2000,
            from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
            to: data.path.clone().stringify(),
            easing: Chartist.Svg.Easing.easeOutQuint
          }
        });
      }
    });
  })();


  // Example Chartist Line Interpolation 
  // -----------------------------------
  (function() {
    var smoothingChart = new Chartist.Line('#exampleSmoothingLine', {
      labels: [1, 2, 3, 4, 5],
      series: [
        [1, 5, 10, 0, 1],
        [10, 15, 0, 1, 2]
      ]
    }, {
      // Remove this configuration to see that chart rendered with cardinal spline interpolation
      // Sometimes, on large jumps in data values, it's better to use simple smoothing.
      lineSmooth: Chartist.Interpolation.simple({
        divisor: 2
      }),
      fullWidth: true,
      chartPadding: {
        right: 20
      },
      low: 0
    });
  })();

  // Example Chartist Bi-Polar Bar
  // -----------------------------
  (function() {
    var biPolarData = {
      labels: ['W1', 'W2', 'W3', 'W4', 'W5', 'W6', 'W7', 'W8', 'W9', 'W10'],
      series: [
        [1, 2, 4, 8, 6, -2, -1, -4, -6, -2]
      ]
    };

    var biPolarOptions = {
      high: 10,
      low: -10,
      axisX: {
        labelInterpolationFnc: function(value, index) {
          return index % 2 === 0 ? value : null;
        }
      }
    };

    new Chartist.Bar('#exampleBiPolarBar', biPolarData, biPolarOptions);

  })();

  // Example Chartist Overlapping Bars
  // ---------------------------------
  (function() {
    var overlappingData = {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      series: [
        [5, 4, 3, 7, 5, 10, 3, 4, 8, 10, 6, 8],
        [3, 2, 9, 5, 4, 6, 4, 6, 7, 8, 7, 4]
      ]
    };

    var overlappingOptions = {
      seriesBarDistance: 10
    };

    var overlappingResponsiveOptions = [
      ['screen and (max-width: 640px)', {
        seriesBarDistance: 5,
        axisX: {
          labelInterpolationFnc: function(value) {
            return value[0];
          }
        }
      }]
    ];

    new Chartist.Bar('#exampleOverlappingBar', overlappingData, overlappingOptions, overlappingResponsiveOptions);

  })();

  // Example Chartist Add Peak Circles
  // ---------------------------------
  (function() {
    // Create a simple bi-polar bar chart
    var peakCirclesChart = new Chartist.Bar('#examplePeakCirclesBar', {
      labels: ['W1', 'W2', 'W3', 'W4', 'W5', 'W6', 'W7', 'W8', 'W9', 'W10'],
      series: [
        [1, 2, 4, 8, 6, -2, -1, -4, -6, -2]
      ]
    }, {
      high: 10,
      low: -10,
      axisX: {
        labelInterpolationFnc: function(value, index) {
          return index % 2 === 0 ? value : null;
        }
      }
    });

    // Listen for draw events on the bar chart
    peakCirclesChart.on('draw', function(data) {
      // If this draw event is of type bar we can use the data to create additional content
      if (data.type === 'bar') {
        // We use the group element of the current series to append a simple circle with the bar peek coordinates and a circle radius that is depending on the value
        data.group.append(new Chartist.Svg('circle', {
          cx: data.x2,
          cy: data.y2,
          r: Math.abs(data.value) * 2 + 5
        }, 'ct-slice-pie'));
      }
    });
  })();


  // Example Chartist Multi-Line Labels
  // ----------------------------------
  (function() {
    new Chartist.Bar('#exampleMultiLabelsBar', {
      labels: ['First quarter of the year', 'Second quarter of the year', 'Third quarter of the year', 'Fourth quarter of the year'],
      series: [
        [60000, 40000, 80000, 70000],
        [40000, 30000, 70000, 65000],
        [8000, 3000, 10000, 6000]
      ]
    }, {
      seriesBarDistance: 10,
      axisX: {
        offset: 60
      },
      axisY: {
        offset: 80,
        labelInterpolationFnc: function(value) {
          return value + ' CHF';
        },
        scaleMinSpace: 15
      }
    });
  })();


  // Example Chartist Stacked Bar Chart
  // ----------------------------------
  (function() {
    new Chartist.Bar('#exampleStackedBar', {
      labels: ['Q1', 'Q2', 'Q3', 'Q4'],
      series: [
        [800000, 1200000, 1400000, 1300000],
        [200000, 400000, 500000, 300000],
        [100000, 200000, 400000, 600000]
      ]
    }, {
      stackBars: true,
      axisY: {
        labelInterpolationFnc: function(value) {
          return (value / 1000) + 'k';
        }
      }
    }).on('draw', function(data) {
      if (data.type === 'bar') {
        data.element.attr({
          style: 'stroke-width: 30px'
        });
      }
    });
  })();


  // Example Chartist Horizontal Bar
  // -------------------------------
  (function() {
    new Chartist.Bar('#exampleHorizontalBar', {
      labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
      series: [
        [5, 4, 3, 7, 5, 10, 3],
        [3, 2, 9, 5, 4, 6, 4]
      ]
    }, {
      seriesBarDistance: 10,
      reverseData: true,
      horizontalBars: true,
      axisY: {
        offset: 70
      }
    });
  })();


  // Example Chartist Extreme Responsive 
  // -----------------------------------
  (function() {
    new Chartist.Bar('#exampleResponsiveBar', {
      labels: ['Quarter 1', 'Quarter 2', 'Quarter 3', 'Quarter 4'],
      series: [
        [5, 4, 3, 7],
        [3, 2, 9, 5],
        [1, 5, 8, 4],
        [2, 3, 4, 6],
        [4, 1, 2, 1]
      ]
    }, {
      // Default mobile configuration
      stackBars: true,
      axisX: {
        labelInterpolationFnc: function(value) {
          return value.split(/\s+/).map(function(word) {
            return word[0];
          }).join('');
        }
      },
      axisY: {
        offset: 20
      }
    }, [
      // Options override for media > 400px
      ['screen and (min-width: 480px)', {
        reverseData: true,
        horizontalBars: true,
        axisX: {
          labelInterpolationFnc: Chartist.noop
        },
        axisY: {
          offset: 60
        }
      }],
      // Options override for media > 800px
      ['screen and (min-width: 992px)', {
        stackBars: false,
        seriesBarDistance: 10
      }],
      // Options override for media > 1000px
      ['screen and (min-width: 1200px)', {
        reverseData: false,
        horizontalBars: false,
        seriesBarDistance: 15
      }]
    ]);
  })();


  // Example Chartist Simple Pie
  // ---------------------------
  (function() {
    var simplePiedata = {
      series: [5, 3, 4]
    };

    var simplePieSum = function(a, b) {
      return a + b;
    };

    new Chartist.Pie('#exampleSimplePie', simplePiedata, {
      labelInterpolationFnc: function(value) {
        return Math.round(value / simplePiedata.series.reduce(simplePieSum) * 100) + '%';
      }
    });
  })();


  // Example Chartist Pie Chart Labels
  // ---------------------------------
  (function() {
    var labelsPieData = {
      labels: ['Bananas', 'Apples', 'Grapes'],
      series: [20, 15, 40]
    };

    var labelsPieOptions = {
      labelInterpolationFnc: function(value) {
        return value[0];
      }
    };

    var labelsPieResponsiveOptions = [
      ['screen and (min-width: 640px)', {
        chartPadding: 30,
        labelOffset: 100,
        labelDirection: 'explode',
        labelInterpolationFnc: function(value) {
          return value;
        }
      }],
      ['screen and (min-width: 1024px)', {
        labelOffset: 80,
        chartPadding: 20
      }]
    ];

    new Chartist.Pie('#exampleLabelsPie', labelsPieData, labelsPieOptions, labelsPieResponsiveOptions);
  })();


  // Example Chartist Gauge Pie
  // --------------------------
  (function() {
    new Chartist.Pie('#exampleGaugePie', {
      series: [20, 10, 30, 40]
    }, {
      donut: true,
      donutWidth: 60,
      startAngle: 270,
      total: 200,
      showLabel: false
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  Chart.defaults.global.responsive = true;


  // Example Chartjs Line
  // --------------------
  (function() {
    var lineChartData = {
      labels: ["January", "February", "March", "April", "May", "June", "July"],
      scaleShowGridLines: true,
      scaleShowVerticalLines: false,
      scaleGridLineColor: "#ebedf0",
      datasets: [{
        fillColor: "rgba(204, 213, 219, .1)",
        strokeColor: $.colors("grey", 400),
        pointColor: $.colors("grey", 400),
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: $.colors("grey", 400),
        data: [65, 59, 80, 81, 56, 55, 40]
      }, {
        fillColor: "rgba(98, 168, 234, .1)",
        strokeColor: $.colors("primary", 600),
        pointColor: $.colors("primary", 600),
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: $.colors("primary", 600),
        data: [28, 48, 40, 19, 86, 27, 90]
      }]
    };

    var myLine = new Chart(document.getElementById("exampleChartjsLine").getContext("2d")).Line(lineChartData);
  })();


  // Example Chartjs Bar
  // --------------------
  (function() {
    var barChartData = {
      labels: ["January", "February", "March", "April", "May"],
      scaleShowGridLines: true,
      scaleShowVerticalLines: false,
      scaleGridLineColor: "#ebedf0",
      barShowStroke: false,
      datasets: [{
        fillColor: $.colors("blue", 500),
        strokeColor: $.colors("blue", 500),
        highlightFill: $.colors("blue", 500),
        highlightStroke: $.colors("blue", 500),
        data: [65, 45, 75, 50, 60]
      }, {
        fillColor: $.colors("grey", 400),
        strokeColor: $.colors("grey", 400),
        highlightFill: $.colors("grey", 400),
        highlightStroke: $.colors("grey", 400),
        data: [30, 20, 40, 25, 45]
      }]
    };

    var myBar = new Chart(document.getElementById("exampleChartjsBar").getContext("2d")).Bar(barChartData);
  })();


  // Example Chartjs Radar
  // --------------------
  (function() {
    var radarChartData = {
      labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Partying", "Running"],
      pointLabelFontSize: 14,
      datasets: [{
        fillColor: "rgba(204,213,219,0.35)",
        strokeColor: "rgba(0,0,0,0)",
        pointColor: $.colors("grey", 400),
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: $.colors("grey", 400),
        data: [65, 59, 90, 81, 56, 55, 40]
      }, {
        fillColor: "rgba(250,122,122,0.25)",
        strokeColor: "rgba(0,0,0,0)",
        pointColor: $.colors("red", 500),
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: $.colors("red", 500),
        data: [28, 48, 40, 19, 96, 27, 100]
      }]
    };

    var myRadar = new Chart(document.getElementById("exampleChartjsRadar").getContext("2d")).Radar(radarChartData, {
      scaleShowLabels: false,
      pointLabelFontSize: 10
    });
  })();


  // Example Chartjs Ploar Area
  // --------------------------
  (function() {
    var chartData = [{
      value: 300,
      color: $.colors("red", 600),
      label: "Red"

    }, {
      value: 200,
      color: $.colors("primary", 500),
      label: "Blue"
    }, {
      value: 100,
      color: $.colors("grey", 300),
      label: "Grey"
    }, {
      value: 50,
      color: $.colors("grey", 400),
      label: "Dark Grey"
    }];

    var myPolarArea = new Chart(document.getElementById("exampleChartjsPloarArea").getContext("2d")).PolarArea(chartData);
  })();


  // Example Chartjs Pie
  // -------------------
  (function() {
    var pieData = [{
      value: 50,
      color: $.colors("primary", 500),
      label: "Blue"
    }, {
      value: 50,
      color: $.colors("grey", 300),
      label: "Grey"
    }];

    var myPie = new Chart(document.getElementById("exampleChartjsPie").getContext("2d")).Pie(pieData);
  })();


  // Example Chartjs Donut
  // ---------------------
  (function() {
    var doughnutData = [{
      value: 45,
      color: $.colors("red", 500),
      label: "Red"
    }, {
      value: 15,
      color: $.colors("grey", 300),
      label: "Grey"
    }, {
      value: 60,
      color: $.colors("primary", 500),
      label: "Blue"
    }];

    var myDoughnut = new Chart(document.getElementById("exampleChartjsDonut").getContext("2d")).Doughnut(doughnutData);
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Example Flot Realtime
  // ---------------------
  (function() {
    if (!$.isFunction($.fn.plot) || $("#exampleFlotRealtime").length === 0) {
      return;
    }

    var data = [];
    var totalPoints = 250;

    function getRandomData() {
      if (data.length > 0) {
        data = data.slice(1);
      }
      // Do a random walk
      while (data.length < totalPoints) {
        var prev = data.length > 0 ? data[data.length - 1] : 50;
        var y = prev + Math.random() * 10 - 5;
        if (y < 0) {
          y = 0;
        } else if (y > 100) {
          y = 100;
        }
        data.push(y);
      }
      // Zip the generated y values with the x values
      var res = [];
      for (var i = 0; i < data.length; ++i) {
        res.push([i, data[i]]);
      }
      return res;
    }
    var labelColor = $.colors("grey", 600);
    // Set up the control widget
    var updateInterval = 30;

    var plot = $.plot($("#exampleFlotRealtime"), [{
      data: getRandomData()
    }], {

      colors: [$.colors("grey", 200)],
      series: {
        shadowSize: 0,
        lines: {
          show: true,
          lineWidth: 0,
          fill: true,
          fillColor: $.colors("grey", 200)
        }
      },
      legend: {
        show: false
      },
      xaxis: {
        show: false,
        font: {
          color: labelColor
        }
      },
      yaxis: {
        tickColor: "#edeff2",
        color: "#474e54",
        min: 0,
        max: 100,
        font: {
          size: 14,
          color: labelColor,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      grid: {
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 0,
          right: 0,
          bottom: 1,
          left: 0
        },
        borderColor: "#eef0f2"
      }
    });

    function update() {
      plot.setData([getRandomData()]);
      // Since the axes don't change, we don't need to call plot.setupGrid()
      plot.draw();
      setTimeout(update, updateInterval);
    }
    update();

  })();


  // Example Flot Full-Bg Line
  // -------------------------
  (function() {
    var b = [
      [1262304000000, 0],
      [1264982400000, 500],
      [1267401600000, 700],
      [1270080000000, 1300],
      [1272672000000, 2600],
      [1275350400000, 1300],
      [1277942400000, 1700],
      [1280620800000, 1300],
      [1283299200000, 1500],
      [1285891200000, 2000],
      [1288569600000, 1500],
      [1291161600000, 1200]
    ];
    var a = [{
      label: "Fish values",
      data: b
    }];

    $.plot("#exampleFlotFullBg", a, {
      xaxis: {
        min: (new Date(2009, 12, 1)).getTime(),
        max: (new Date(2010, 11, 2)).getTime(),
        mode: "time",
        tickSize: [1, "month"],
        monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        tickLength: 0,
        // tickColor: "#edeff2",
        color: "#474e54",
        font: {
          size: 14,
          weight: 300
            // family: "OpenSans Light"
        }
      },
      yaxis: {
        tickColor: "#edeff2",
        color: "#474e54",
        font: {
          size: 14,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      series: {
        shadowSize: 0,
        lines: {
          show: true,
          // fill: true,
          // fillColor: "#ff0000",
          lineWidth: 1.5
        },
        points: {
          show: true,
          fill: true,
          fillColor: $.colors("primary", 600),
          radius: 3,
          lineWidth: 1
        }
      },
      colors: [$.colors("primary", 400)],
      grid: {
        // show: true,
        hoverable: true,
        clickable: true,
        // color: "green",
        // tickColor: "red",
        backgroundColor: {
          colors: ["#fcfdfe", "#fcfdfe"]
        },
        borderWidth: 0
          // borderColor: "#ff0000"
      },
      legend: {
        show: false
      }
    });

  })();


  // Example Flot Curve
  // ------------------
  (function() {
    var dt1 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.25) {
      dt1.push([i, Math.sin(i)]);
    }

    var dt2 = [];
    for (i = 0; i < Math.PI * 2; i += 0.25) {
      dt2.push([i, Math.cos(i)]);
    }

    var f_chart = $("#exampleFlotCurve");

    $.plot(f_chart, [{
      label: "sin(x)",
      data: dt1,
      color: $.colors("primary", 400),
      points: {
        show: true,
        fill: true,
        radius: 3,
        fillColor: $.colors("primary", 400)
      }
    }, {
      label: "cos(x)",
      data: dt2,
      yaxis: 2,
      color: $.colors("green", 400),
      points: {
        show: true,
        fill: true,
        radius: 3,
        fillColor: $.colors("green", 600)
      }
    }, ], {
      series: {
        shadowSize: 0,
        lines: {
          show: true,
          lineWidth: 1.5
        },
        points: {
          show: true,
          radius: 3,
          lineWidth: 1
        }
      },
      xaxes: [{
          ticks: [
            0, [Math.PI / 2, "\u03c0/2"],
            [Math.PI, "\u03c0"],
            [Math.PI * 3 / 2, "3\u03c0/2"],
            [Math.PI * 2, "2\u03c0"]
          ]
        }

      ],
      yaxes: [{
        ticks: 5,
        min: -2,
        max: 2,
        tickDecimals: 3
      }, {
        ticks: 5,
        min: -1,
        max: 1,
        tickLength: 0,
        tickDecimals: 2,
        position: "right"
      }],
      grid: {
        hoverable: true,
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 1,
          right: 1,
          bottom: 1,
          left: 1
        },
        borderColor: "#eef0f2"
      },
      legend: {
        show: false
      }
    });

  })();


  // Example Flot Mix
  // ----------------
  (function() {
    var b1 = [];
    for (var i = 0; i < 14; i += 0.5) {
      b1.push([i, Math.cos(i) + 1]);
    }

    var b2 = [
      [2, 3],
      [4, 8],
      [6, 5],
      [9, 13]
    ];

    var b3 = [];
    for (i = 0; i < 14; i += 0.5) {
      b3.push([i, Math.cos(i) + Math.sin(i) - 1]);
    }

    var b4 = [];
    for (i = 0; i < 14; i += 0.1) {
      b4.push([i, Math.sqrt(i * 10) - 4 * Math.cos(i)]);
    }

    var b5 = [];
    for (i = 0; i < 14; i += 1.5) {
      b5.push([i, (Math.cos(i) + 2 * Math.sin(i)) + 6]);
    }

    var b6 = [];
    for (i = 0; i < 14; i += 0.5 + Math.random()) {
      b6.push([i, Math.sqrt(i + 2 * Math.cos(i)) - Math.sin(i) - 3]);
    }

    $.plot("#exampleFlotMix", [{
      data: b2,
      bars: {
        show: true,
        align: "center",
        fill: true,
        fillColor: $.colors("grey", 200)
      }
    }, {
      data: b1,
      lines: {
        show: true,
        fill: true,
        fillColor: "rgba(251,213,181,.1)"
      }
    }, {
      data: b3,
      points: {
        show: true,
        fill: true,
        fillColor: $.colors("green", 600),
        radius: 2
      }
    }, {
      data: b4,
      lines: {
        show: true
      },
      points: {
        show: false
      }
    }, {
      data: b5,
      lines: {
        show: true
      },
      points: {
        show: true,
        fill: true,
        fillColor: $.colors("primary", 600),
        radius: 2
      }
    }, {
      data: b6,
      lines: {
        show: true,
        steps: true
      }
    }], {
      xaxis: {
        tickLength: 0,
        color: "#474e54",
        font: {
          size: 14,
          weight: 300
            // family: "OpenSans Light"
        }
      },
      yaxis: {
        tickColor: "#edeff2",
        color: "#474e54",
        font: {
          size: 14,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      grid: {
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 0,
          right: 0,
          bottom: 1,
          left: 0
        },
        borderColor: "#eef0f2"
      },
      series: {
        shadowSize: 0
      },
      colors: [$.colors("grey", 200),
        $.colors("orange", 200),
        $.colors("green", 600),
        $.colors("yellow", 600),
        $.colors("primary", 600),
        $.colors("purple", 200)
      ]
    });
  })();


  // Example Flot Stack Bar
  // ----------------------
  (function() {
    var a1 = [];
    for (var i = 1; i <= 4; i += 1) {
      a1.push([i, parseInt(Math.random() * 30)]);
    }

    var a2 = [];
    for (i = 1; i <= 4; i += 1) {
      a2.push([i, parseInt(Math.random() * 30)]);
    }

    var a3 = [];
    for (i = 1; i <= 4; i += 1) {
      a3.push([i, parseInt(Math.random() * 30)]);
    }

    var a4 = [];
    for (i = 1; i <= 4; i += 1) {
      a4.push([i, parseInt(Math.random() * 30 - 10)]);
    }

    $.plot("#exampleFlotStackBar", [{
      data: a1,
      bars: {
        fill: true,
        fillColor: $.colors("light-green", 500)
      }
    }, {
      data: a2,
      bars: {
        fill: true,
        fillColor: $.colors("grey", 400)
      }
    }, {
      data: a3,
      bars: {
        fill: true,
        fillColor: $.colors("primary", 500)
      }
    }, {
      data: a4,
      bars: {
        fill: true,
        fillColor: $.colors("purple", 500)
      }
    }], {
      series: {
        stack: true,
        shadowSize: 0,
        lines: {
          show: false,
          fill: true,
          steps: false
        },
        bars: {
          show: true,
          align: "center",
          barWidth: 0.38
        }
      },
      colors: [$.colors("light-green", 500), $.colors("grey", 400), $.colors("primary", 500), $.colors("purple", 500)],
      xaxis: {
        tickLength: 0,
        color: "#474e54",
        min: 0,
        max: 5.5,
        ticks: [1, 2, 3, 4, ],
        font: {
          size: 14,
          weight: 300
            // family: "OpenSans Light"
        }
      },
      yaxis: {
        tickColor: "#edeff2",
        color: "#474e54",
        min: -10,
        font: {
          size: 14,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      grid: {
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 0,
          right: 0,
          bottom: 1,
          left: 0
        },
        borderColor: "#eef0f2"
      }
    });
  })();


  // Example Flot Horizontal Bar
  // ---------------------------
  (function() {
    var a11 = [];
    for (var i = 1; i <= 5; i += 1) {
      a11.push([parseInt(Math.random() * 30), i]);
    }

    var a22 = [];
    for (i = 1; i <= 5; i += 1) {
      a22.push([parseInt(Math.random() * 30), i]);
    }

    var a33 = [];
    for (i = 1; i <= 5; i += 1) {
      a33.push([parseInt(Math.random() * 30), i]);
    }

    $.plot("#exampleFlotHorizontalBar", [{
      data: a11,
      bars: {
        fill: true,
        fillColor: $.colors("primary", 500)
      }
    }, {
      data: a22,
      bars: {
        fill: true,
        fillColor: $.colors("grey", 400)
      }
    }, {
      data: a33,
      bars: {
        fill: true,
        fillColor: $.colors("red", 500)
      }
    }], {
      series: {
        stack: true,
        lines: {
          show: false,
          fill: true,
        },
        bars: {
          show: true,
          barWidth: 0.55,
          align: "center",
          horizontal: true
        }
      },
      colors: [$.colors("primary", 500), $.colors("grey", 400), $.colors("red", 500)],
      xaxis: {
        color: "#474e54",
        font: {
          size: 14,
          weight: 300
            // family: "OpenSans Light"
        }
      },
      yaxis: {
        tickLength: 0,
        tickColor: "#edeff2",
        color: "#474e54",
        min: 0,
        max: 6,
        ticks: [1, 2, 3, 4, 5],
        font: {
          size: 14,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      grid: {
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 1,
          right: 1,
          bottom: 1,
          left: 1
        },
        borderColor: "#eef0f2"
      }
    });
  })();

  // Example Flot Pie
  // ----------------
  (function() {
    var pieData = [],
      series = 2;

    for (var i = 0; i < series; i++) {
      pieData[i] = {
        label: "Example Pie S" + (i + 1),
        data: Math.floor(Math.random() * 100) + 1
      };
    }

    var placeholder = $("#exampleFlotPie");

    // Default Options
    $("#btnPieDefault").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            show: true
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)]
      });
    });

    // Without Legend
    $("#btnPieWithoutLegend").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            show: true,
            label: {
              show: true
            }
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)],
        legend: {
          show: false
        }
      });
    });

    // Label Radius
    $("#btnPieLabelRadius").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            show: true,
            radius: 1,
            label: {
              show: true,
              radius: 3 / 4,
              formatter: labelFormatter
            }
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)],
        legend: {
          show: false
        }
      });
    });

    // Rectangular Pie
    $("#btnPieRectangular").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            show: true,
            radius: 500,
            label: {
              show: true,
              formatter: labelFormatter,
              threshold: 0.1
            }
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)],
        legend: {
          show: false
        }
      });
    });

    // Donut Hole
    $("#btnPieDonutHole").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            innerRadius: 0.5,
            show: true
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)]
      });
    });

    // Interactivity
    $("#btnPieInteractivity").click(function() {
      placeholder.unbind();

      $.plot(placeholder, pieData, {
        series: {
          pie: {
            show: true
          }
        },
        colors: [$.colors("primary", 500), $.colors("grey", 300)],
        grid: {
          hoverable: true,
          clickable: true
        }

      });

      placeholder.bind("plothover", function(event, pos, obj) {
        if (!obj) {
          return;
        }

        var percent = parseFloat(obj.series.percent).toFixed(2);
        $("#hover").html("<span style='font-weight:bold; color:" + obj.series.color + "'>" + obj.series.label + " (" + percent + "%)</span>");
      });

      placeholder.bind("plotclick", function(event, pos, obj) {
        if (!obj) {
          return;
        }

        percent = parseFloat(obj.series.percent).toFixed(2);
        alert("" + obj.series.label + ": " + percent + "%");
      });
    });

    // Show the initial default chart
    $("#btnPieDefault").click();

    // A custom label formatter used by several of the plots
    console.log("out");

    function labelFormatter(label, series) {
      return "<div" + " style='" + "font-size: 8pt; text-align: center; padding: 2px; color: #747474;'" + ">" + label + "<br/>" + Math.round(series.percent) + "%</div>";
    }
  })();


  // Example Flot Visitors
  // ---------------------
  (function() {
    var d = [
      [1196463600000, 0],
      [1196550000000, 0],
      [1196636400000, 0],
      [1196722800000, 77],
      [1196809200000, 3636],
      [1196895600000, 3575],
      [1196982000000, 2736],
      [1197068400000, 1086],
      [1197154800000, 676],
      [1197241200000, 1205],
      [1197327600000, 906],
      [1197414000000, 710],
      [1197500400000, 639],
      [1197586800000, 540],
      [1197673200000, 435],
      [1197759600000, 301],
      [1197846000000, 575],
      [1197932400000, 481],
      [1198018800000, 591],
      [1198105200000, 608],
      [1198191600000, 459],
      [1198278000000, 234],
      [1198364400000, 1352],
      [1198450800000, 686],
      [1198537200000, 279],
      [1198623600000, 449],
      [1198710000000, 468],
      [1198796400000, 392],
      [1198882800000, 282],
      [1198969200000, 208],
      [1199055600000, 229],
      [1199142000000, 177],
      [1199228400000, 374],
      [1199314800000, 436],
      [1199401200000, 404],
      [1199487600000, 253],
      [1199574000000, 218],
      [1199660400000, 476],
      [1199746800000, 462],
      [1199833200000, 448],
      [1199919600000, 442],
      [1200006000000, 403],
      [1200092400000, 204],
      [1200178800000, 194],
      [1200265200000, 327],
      [1200351600000, 374],
      [1200438000000, 507],
      [1200524400000, 546],
      [1200610800000, 482],
      [1200697200000, 283],
      [1200783600000, 221],
      [1200870000000, 483],
      [1200956400000, 523],
      [1201042800000, 528],
      [1201129200000, 483],
      [1201215600000, 452],
      [1201302000000, 270],
      [1201388400000, 222],
      [1201474800000, 439],
      [1201661200000, 559],
      [1201647600000, 521],
      [1201734000000, 477],
      [1201820400000, 442],
      [1201906800000, 252],
      [1201993200000, 236],
      [1202079600000, 525],
      [1202166000000, 477],
      [1202252400000, 386],
      [1202338800000, 409],
      [1202425200000, 408],
      [1202511600000, 237],
      [1202598000000, 193],
      [1202684400000, 357],
      [1202770800000, 414],
      [1202857200000, 393],
      [1202943600000, 353],
      [1203030000000, 364],
      [1203116400000, 215],
      [1203202800000, 214],
      [1203289200000, 356],
      [1203375600000, 399],
      [1203462000000, 334],
      [1203548400000, 348],
      [1203634800000, 243],
      [1203721200000, 126],
      [1203807600000, 157],
      [1203894000000, 288]
    ];

    // first correct the timestamps - they are recorded as the daily
    // midnights in UTC+0100, but Flot always displays dates in UTC
    // so we have to add one hour to hit the midnights in the plot

    for (var i = 0; i < d.length; ++i) {
      d[i][0] += 60 * 60 * 1000;
    }

    // helper for returning the weekends in a period

    function weekendAreas(axes) {

      var markings = [],
        d = new Date(axes.xaxis.min);

      // go to the first Saturday

      d.setUTCDate(d.getUTCDate() - ((d.getUTCDay() + 1) % 7));
      d.setUTCSeconds(0);
      d.setUTCMinutes(0);
      d.setUTCHours(0);

      var i = d.getTime();

      // when we don't set yaxis, the rectangle automatically
      // extends to infinity upwards and downwards

      do {
        markings.push({
          xaxis: {
            from: i,
            to: i + 2 * 24 * 60 * 60 * 1000
          }
        });
        i += 7 * 24 * 60 * 60 * 1000;
      } while (i < axes.xaxis.max);

      return markings;
    }

    var options = {
      series: {
        lines: {
          show: true,
          lineWidth: 1
        },
        shadowSize: 0
      },
      colors: [$.colors("primary", 600)],
      selection: {
        mode: "x",
        color: [$.colors("primary", 300)]
      },
      xaxis: {
        tickLength: 0,
        mode: "time",
        color: "#474e54",
        font: {
          size: 14,
          weight: 300
            // family: "OpenSans Light"
        }
      },
      yaxis: {
        tickColor: "#edeff2",
        color: "#474e54",
        font: {
          size: 14,
          weight: "300"
            // family: "OpenSans Light"
        }
      },
      grid: {
        markings: weekendAreas,
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 0,
          right: 0,
          bottom: 1,
          left: 0
        },
        borderColor: "#eef0f2"
      }
    };

    var _plot = $.plot("#exampleFlotVisitors", [d], options);

    var overview = $.plot("#exampleFlotVisitorsOverview", [d], {
      series: {
        lines: {
          show: true,
          lineWidth: 1
        },
        shadowSize: 0
      },
      colors: [$.colors("primary", 600)],
      xaxis: {
        ticks: [],
        mode: "time"
      },
      yaxis: {
        ticks: [],
        min: 0,
        autoscaleMargin: 0.1
      },
      selection: {
        mode: "x",
        color: [$.colors("primary", 300)]
      },
      grid: {
        // markings: weekendAreas,
        color: "#474e54",
        tickColor: "#e3e6ea",
        backgroundColor: {
          colors: ["#fff", "#fff"]
        },
        borderWidth: {
          top: 1,
          right: 1,
          bottom: 1,
          left: 1
        },
        borderColor: "#eef0f2"
      }
    });

    // now connect the two
    $("#exampleFlotVisitors").bind("plotselected", function(event, ranges) {

      // do the zooming
      $.each(_plot.getXAxes(), function(_, axis) {
        var opts = axis.options;
        opts.min = ranges.xaxis.from;
        opts.max = ranges.xaxis.to;
      });
      _plot.setupGrid();
      _plot.draw();
      _plot.clearSelection();

      // don't fire event on the overview to prevent eternal loop
      overview.setSelection(ranges, true);
    });

    $("#exampleFlotVisitorsOverview").bind("plotselected", function(event, ranges) {
      _plot.setSelection(ranges);
    });
  })();

  // Example Flot Tooltip
  // --------------------
  (function() {
    $("<div class='flot-tooltip'></div>").css({
      position: "absolute",
      color: "#fff",
      display: "none",
      border: "1px solid #777",
      padding: "2px",
      "background-color": "#777",
      opacity: 0.80
    }).appendTo("body");


    $("#exampleFlotCurve").bind("plothover", function(event, pos, item) {
      if (item) {
        var x = item.datapoint[0].toFixed(2),
          y = item.datapoint[1].toFixed(2);
        $(".flot-tooltip").html(item.series.label + " of " + x + " = " + y)
          .css({
            top: item.pageY + 5,
            left: item.pageX + 5
          })
          .fadeIn(200);
      } else {
        $(".flot-tooltip").hide();
      }
    });

    $("#exampleFlotFullBg").bind("plothover", function(event, pos, item) {
      if (item) {
        var x = item.datapoint[0].toFixed(2),
          y = item.datapoint[1].toFixed(2);
        $(".flot-tooltip").html(item.series.label + " of " + x + " = " + y)
          .css({
            top: item.pageY + 5,
            left: item.pageX + 5
          })
          .fadeIn(200);
      } else {
        $(".flot-tooltip").hide();
      }
    });

    $("#exampleFlotRealtime").bind("plothover", function(event, pos, item) {
      if (item) {
        var x = item.datapoint[0].toFixed(2),
          y = item.datapoint[1].toFixed(2);
        $(".flot-tooltip").html("x | " + x + "," + " y | " + y)
          .css({
            top: item.pageY + 5,
            left: item.pageX + 5
          })
          .fadeIn(200);
      } else {
        $(".flot-tooltip").hide();
      }
    });

  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Example Gauge Dynamic
  // ---------------------
  $(document).ready(function($) {
    var dynamicGauge = $("#exampleDynamicGauge").data('gauge');

    setInterval(function() {
      var random = Math.round(Math.random() * 1000);

      var options = {
        strokeColor: $.colors("primary", 500)
      };
      if (random > 700) {
        options.strokeColor = $.colors("pink", 500);
      } else if (random < 300) {
        options.strokeColor = $.colors("green", 500);
      }

      dynamicGauge.setOptions(options)
        .set(random);
    }, 1500);
  });

  // Example Donut Dynamic
  // ---------------------
  $(document).ready(function($) {
    var dynamicDonut = $("#exampleDynamicDonut").data('donut');

    setInterval(function() {
      var random = Math.round(Math.random() * 1000);

      var options = {
        strokeColor: $.colors("primary", 500)
      };
      if (random > 700) {
        options.strokeColor = $.colors("pink", 500);
      } else if (random < 300) {
        options.strokeColor = $.colors("green", 500);
      }

      dynamicDonut.setOptions(options)
        .set(random);
    }, 1500);
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  // Example Morris Line
  // -------------------
  (function() {
    Morris.Line({
      element: 'exampleMorrisLine',
      data: [{
        "y": "2016 Q3",
        "a": 40,
        "b": 100
      }, {
        "y": "2016 Q2",
        "a": 75,
        "b": 165
      }, {
        "y": "2016 Q1",
        "a": 150,
        "b": 240
      }, {
        "y": "2014 Q4",
        "a": 100,
        "b": 270
      }, {
        "y": "2013 Q4",
        "a": 260,
        "b": 300
      }, {
        "y": "2012 Q4",
        "a": 20,
        "b": 225
      }, {
        "y": "2011 Q4",
        "a": 295,
        "b": 110
      }],
      xkey: 'y',
      ykeys: ['a', 'b'],
      labels: ['Series A', 'Series B'],
      resize: true,
      pointSize: 3,
      smooth: true,
      gridTextColor: '#474e54',
      gridLineColor: '#eef0f2',
      goalLineColors: '#e3e6ea',
      gridTextFamily: $.configs.get('site', 'fontFamily'),
      gridTextWeight: '300',
      numLines: 7,
      gridtextSize: 14,
      lineWidth: 1,
      lineColors: [$.colors("green", 600), $.colors("primary", 600)]
    });
  })();


  // Example Morris Area
  // -------------------
  (function() {
    Morris.Area({
      element: 'exampleMorrisArea',
      data: [{
        y: '6',
        a: 270,
        b: 160
      }, {
        y: '7',
        a: 210,
        b: 110
      }, {
        y: '8',
        a: 240,
        b: 130
      }, {
        y: '9',
        a: 280,
        b: 250
      }, {
        y: '10',
        a: 210,
        b: 140
      }, {
        y: '11',
        a: 150,
        b: 90
      }, {
        y: '12',
        a: 290,
        b: 180
      }, {
        y: '13',
        a: 280,
        b: 240
      }],
      xkey: 'y',
      ykeys: ['a', 'b'],
      labels: ['Series A', 'Series B'],
      behaveLikeLine: true,
      ymax: 300,
      resize: true,
      pointSize: 3,
      smooth: true,
      gridTextColor: '#474e54',
      gridLineColor: '#eef0f2',
      goalLineColors: '#e3e6ea',
      gridTextFamily: $.configs.get('site', 'fontFamily'),
      gridTextWeight: '300',
      numLines: 7,
      gridtextSize: 14,
      lineWidth: 1,
      fillOpacity: 0.1,
      lineColors: [$.colors("primary", 600), $.colors("green", 600)]
    });
  })();


  // Example Morris Bar
  // ------------------
  (function() {
    Morris.Bar({
      element: 'exampleMorrisBar',
      data: [{
          y: '2013-6',
          a: 350,
          b: 410
        }, {
          y: '2013-7',
          a: 110,
          b: 300
        }, {
          y: '2013-8',
          a: 460,
          b: 130
        }, {
          y: '2013-9',
          a: 250,
          b: 310
        }
        // { y: '2013-10', a: 50, b: 40 },
        // { y: '2013-11', a: 75, b: 65 },
        // { y: '2013-12', a: 100, b: 90 }
      ],
      xkey: 'y',
      ykeys: ['a', 'b'],
      labels: ['Series A', 'Series B'],
      barGap: 6,
      barSizeRatio: 0.35,
      smooth: true,
      gridTextColor: '#474e54',
      gridLineColor: '#eef0f2',
      goalLineColors: '#e3e6ea',
      gridTextFamily: $.configs.get('site', 'fontFamily'),
      gridTextWeight: '300',
      numLines: 6,
      gridtextSize: 14,
      resize: true,
      barColors: [$.colors("red", 500), $.colors("grey", 400)]
    });
  })();


  // Example Morris Donut
  // --------------------
  (function() {
    Morris.Donut({
      element: 'exampleMorrisDonut',
      data: [{
        label: "Download Sales",
        value: 35
      }, {
        label: "In-Store Sales",
        value: 48
      }, {
        label: "Mail-Order Sales",
        value: 22
      }, ],
      // barSizeRatio: 0.35,
      resize: true,
      colors: [$.colors("red", 500), $.colors("primary", 500), $.colors("grey", 400)]
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Peity Default
  // ---------------------
  (function() {
    /* dynamic example */
    var dynamicChart = $("#examplePeityDynamic").peity("line", {
      width: 64,
      fill: [$.colors("primary", 200)],
      stroke: $.colors("primary", 500),
      height: 22
    });

    setInterval(function() {
      var random = Math.round(Math.random() * 10);
      var values = dynamicChart.text().split(",");
      values.shift();
      values.push(random);

      dynamicChart
        .text(values.join(","))
        .change();
    }, 1000);
  })();


  // Example Peity Red
  // -------------------
  (function() {
    /* dynamic example */
    var dynamicRedChart = $("#examplePeityDynamicRed").peity("line", {
      width: 64,
      fill: [$.colors("red", 200)],
      stroke: $.colors("red", 500),
      height: 22
    });

    setInterval(function() {
      var random = Math.round(Math.random() * 10);
      var values = dynamicRedChart.text().split(",");
      values.shift();
      values.push(random);

      dynamicRedChart
        .text(values.join(","))
        .change();
    }, 1000);
  })();


  // Example Peity Green
  // -------------------
  (function() {
    /* dynamic example */
    var dynamicGreenChart = $("#examplePeityDynamicGreen").peity("line", {
      width: 64,
      fill: [$.colors("green", 200)],
      stroke: $.colors("green", 500),
      height: 22
    });

    setInterval(function() {
      var random = Math.round(Math.random() * 10);
      var values = dynamicGreenChart.text().split(",");
      values.shift();
      values.push(random);

      dynamicGreenChart
        .text(values.join(","))
        .change();
    }, 1000);
  })();


  // Example Peity Orange
  // --------------------
  (function() {
    /* dynamic example */
    var dynamicOrangeChart = $("#examplePeityDynamicOrange").peity("line", {
      width: 64,
      fill: [$.colors("orange", 200)],
      stroke: $.colors("orange", 500),
      height: 22
    });

    setInterval(function() {
      var random = Math.round(Math.random() * 10);
      var values = dynamicOrangeChart.text().split(",");
      values.shift();
      values.push(random);

      dynamicOrangeChart
        .text(values.join(","))
        .change();
    }, 1000);
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  $(document).ready(function($) {
    Site.run();
  });

  // Example Api Methods
  // -------------------
  (function() {
    var $example = $('#examplePieApi');

    $('.pie-api-start').on('click', function() {
      $example.asPieProgress('start');
    });
    $('.pie-api-finish').on('click', function() {
      $example.asPieProgress('finish');
    });
    $('.pie-api-go').on('click', function() {
      $example.asPieProgress('go', 200);
    });
    $('.pie-api-go_percentage').on('click', function() {
      $example.asPieProgress('go', '50%');
    });
    $('.pie-api-stop').on('click', function() {
      $example.asPieProgress('stop');
    });
    $('.pie-api-reset').on('click', function() {
      $example.asPieProgress('reset');
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  $(document).ready(function($) {
    Site.run();
  });

  // Example Lines
  // -------------
  (function() {
    var seriesData = [
      [],
      [],
      []
    ];
    var random = new Rickshaw.Fixtures.RandomData(150);

    for (var i = 0; i < 150; i++) {
      random.addData(seriesData);
    }

    var $element = $('#exampleChart');
    var graph = new Rickshaw.Graph({
      element: $element.get(0),
      width: $element.width(),
      height: 300,
      renderer: 'line',
      series: [{
        color: $.colors("primary", 500),
        data: seriesData[0],
        name: 'New York'
      }, {
        color: $.colors("red", 500),
        data: seriesData[1],
        name: 'London'
      }, {
        color: $.colors("green", 500),
        data: seriesData[2],
        name: 'Tokyo'
      }]
    });

    graph.render();

    setInterval(function() {
      random.removeData(seriesData);
      random.addData(seriesData);
      graph.update();

    }, 2000);

    var hoverDetail = new Rickshaw.Graph.HoverDetail({
      graph: graph
    });

    var legend = new Rickshaw.Graph.Legend({
      graph: graph,
      element: document.getElementById('exampleChartLegend')

    });

    var shelving = new Rickshaw.Graph.Behavior.Series.Toggle({
      graph: graph,
      legend: legend
    });

    var axes = new Rickshaw.Graph.Axis.Time({
      graph: graph
    });
    axes.render();

    $(window).on('resize', function() {
      graph.configure({
        width: $element.width()
      });
      graph.render();
    });
  })();


  // Example Scatter Plot
  // --------------------
  (function() {
    var seriesData = [
      [],
      [],
      []
    ];
    var random = new Rickshaw.Fixtures.RandomData(150);

    for (var i = 0; i < 150; i++) {
      random.addData(seriesData);
    }

    var $element = $('#exampleScatterChart');
    var graph = new Rickshaw.Graph({
      element: $element.get(0),
      width: $element.width(),
      height: 300,
      renderer: 'scatterplot',
      series: [{
        color: $.colors("primary", 500),
        data: seriesData[0],
        name: 'New York'
      }, {
        color: $.colors("red", 500),
        data: seriesData[1],
        name: 'London'
      }, {
        color: $.colors("green", 500),
        data: seriesData[2],
        name: 'Tokyo'
      }]
    });

    graph.render();

    var hoverDetail = new Rickshaw.Graph.HoverDetail({
      graph: graph
    });

    var legend = new Rickshaw.Graph.Legend({
      graph: graph,
      element: document.getElementById('exampleScatterLegend')
    });

    var shelving = new Rickshaw.Graph.Behavior.Series.Toggle({
      graph: graph,
      legend: legend
    });

    $(window).on('resize', function() {
      graph.configure({
        width: $element.width()
      });
      graph.render();
    });

  })();

  // Example Stacked Bars
  // --------------------
  (function() {
    var seriesData = [
      [],
      [],
      []
    ];
    var random = new Rickshaw.Fixtures.RandomData(150);

    for (var i = 0; i < 150; i++) {
      random.addData(seriesData);
    }

    var $element = $('#exampleStackedChart');
    var graph = new Rickshaw.Graph({
      element: $element.get(0),
      width: $element.width(),
      height: 300,
      renderer: 'bar',
      series: [{
        color: $.colors("primary", 700),
        data: seriesData[0],
        name: 'New York'
      }, {
        color: $.colors("primary", 500),
        data: seriesData[1],
        name: 'London'
      }, {
        color: $.colors("primary", 300),
        data: seriesData[2],
        name: 'Tokyo'
      }]
    });

    graph.render();

    setInterval(function() {
      random.removeData(seriesData);
      random.addData(seriesData);
      graph.update();

    }, 2000);

    var hoverDetail = new Rickshaw.Graph.HoverDetail({
      graph: graph
    });

    var legend = new Rickshaw.Graph.Legend({
      graph: graph,
      element: document.getElementById('exampleStackedLegend')
    });

    var shelving = new Rickshaw.Graph.Behavior.Series.Toggle({
      graph: graph,
      legend: legend
    });

    $(window).on('resize', function() {
      graph.configure({
        width: $element.width()
      });
      graph.render();
    });
  })();

  // Example Area
  // ------------
  (function() {
    var seriesData = [
      [],
      [],
      []
    ];
    var random = new Rickshaw.Fixtures.RandomData(150);

    for (var i = 0; i < 150; i++) {
      random.addData(seriesData);
    }

    var $element = $('#exampleAreaChart');
    var graph = new Rickshaw.Graph({
      element: $element.get(0),
      width: $element.width(),
      height: 300,
      renderer: 'area',
      stroke: true,
      series: [{
        color: $.colors("purple", 700),
        data: seriesData[0],
        name: 'New York'
      }, {
        color: $.colors("purple", 500),
        data: seriesData[1],
        name: 'London'
      }, {
        color: $.colors("purple", 300),
        data: seriesData[2],
        name: 'Tokyo'
      }]
    });

    graph.render();

    setInterval(function() {
      random.removeData(seriesData);
      random.addData(seriesData);
      graph.update();

    }, 2000);

    var hoverDetail = new Rickshaw.Graph.HoverDetail({
      graph: graph
    });

    var legend = new Rickshaw.Graph.Legend({
      graph: graph,
      element: document.getElementById('exampleAreaLegend')
    });

    var shelving = new Rickshaw.Graph.Behavior.Series.Toggle({
      graph: graph,
      legend: legend
    });

    $(window).on('resize', function() {
      graph.configure({
        width: $element.width()
      });
      graph.render();
    });

  })();

  // Example Multiple Renderers
  // ---------------------------
  (function() {
    var seriesData = [
      [],
      [],
      [],
      [],
      []
    ];
    var random = new Rickshaw.Fixtures.RandomData(50);

    for (var i = 0; i < 75; i++) {
      random.addData(seriesData);
    }

    var $element = $('#exampleMultipleChart');
    var graph = new Rickshaw.Graph({
      element: $element.get(0),
      width: $element.width(),
      height: 300,
      renderer: 'multi',
      dotSize: 5,
      series: [{
        name: 'temperature',
        data: seriesData.shift(),
        color: $.colors("green", 500),
        renderer: 'stack'
      }, {
        name: 'heat index',
        data: seriesData.shift(),
        color: $.colors("cyan", 500),
        renderer: 'stack'
      }, {
        name: 'dewpoint',
        data: seriesData.shift(),
        color: $.colors("blue", 500),
        renderer: 'scatterplot'
      }, {
        name: 'pop',
        data: seriesData.shift().map(function(d) {
          return {
            x: d.x,
            y: d.y / 4
          }
        }),
        color: $.colors("indigo", 500),
        renderer: 'bar'
      }, {
        name: 'humidity',
        data: seriesData.shift().map(function(d) {
          return {
            x: d.x,
            y: d.y * 1.5
          }
        }),
        renderer: 'line',
        color: $.colors("red", 500)
      }]
    });

    var slider = new Rickshaw.Graph.RangeSlider.Preview({
      graph: graph,
      element: document.querySelector('#exampleMultipleSlider')
    });

    graph.render();

    var detail = new Rickshaw.Graph.HoverDetail({
      graph: graph
    });

    var legend = new Rickshaw.Graph.Legend({
      graph: graph,
      element: document.querySelector('#exampleMultipleLegend')
    });

    var highlighter = new Rickshaw.Graph.Behavior.Series.Highlight({
      graph: graph,
      legend: legend,
      disabledColor: function() {
        return 'rgba(0, 0, 0, 0.2)'
      }
    });

    var highlighter = new Rickshaw.Graph.Behavior.Series.Toggle({
      graph: graph,
      legend: legend
    });

    $(window).on('resize', function() {
      graph.configure({
        width: $element.width()
      });
      graph.render();
    });
  })();
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Sparkline Basic
  // ---------------
  // Pie Chart
  $(".sparkline-pie-chart").sparkline([4, 2, 6], {
    type: 'pie',
    height: '162px',
    sliceColors: [$.colors("primary", 500), $.colors("primary", 700), $.colors("primary", 600)]
  });

  // line chart
  $(".sparkline-line-chart").sparkline([1, 3, 4, 2, 3, 6, 5, 3], {
    type: 'line',
    height: '162px',
    width: '200px',
    normalRangeMin: 0,
    spotRadius: 2,
    spotColor: $.colors("red", 600),
    highlightSpotColor: $.colors("red", 700),
    lineColor: $.colors("red", 500),
    highlightLineColor: $.colors("red", 500),
    fillColor: $.colors("red", 100)
  });

  // bar chart
  $(".sparkline-bar-chart").sparkline([4, 7, 3, 2, 5, 6, 8, 5, 4, 8], {
    type: 'bar',
    height: '162px',
    barWidth: 10,
    barSpacing: 6,
    barColor: $.colors("primary", 500),
    negBarColor: $.colors("primary", 600)
  });

  // composite bar chart
  $('.sparkline-compositebar-chart').sparkline('html', {
    type: 'bar',
    height: '162px',
    barWidth: 10,
    barSpacing: 5,
    barColor: $.colors("grey", 400)
  });

  $('.sparkline-compositebar-chart').sparkline([4, 5, 6, 6, 5, 5, 3, 6, 4, 2], {
    composite: true,
    fillColor: false,
    lineColor: $.colors("purple", 400)
  });

  $('.sparkline-compositebar-chart').sparkline([1, 4, 5, 2, 3, 5, 6, 1, 3, 6], {
    composite: true,
    fillColor: false,
    lineColor: $.colors("red", 400)
  });


  // Sparkline Types
  // ---------------
  // Line charts taking their values from the tag
  $('.sparkline-line').sparkline('html', {
    height: '32px',
    width: '150px',
    lineColor: $.colors("red", 600),
    fillColor: $.colors("red", 100)
  });

  // Bar charts using inline values
  $('.sparkline-bar').sparkline('html', {
    type: 'bar',
    height: '32px',
    barWidth: 10,
    barSpacing: 5,
    barColor: $.colors("primary", 500),
    negBarColor: $.colors("red", 500),
    stackedBarColor: [$.colors("primary", 500), $.colors("red", 500)]
  });

  // Composite line charts, the second using values supplied via javascript
  $('.sparkline-compositeline').sparkline('html', {
    height: '32px',
    width: '150px',
    fillColor: false,
    lineColor: $.colors("primary", 500),
    spotColor: $.colors("green", 500),
    minSpotColor: $.colors("primary", 500),
    maxSpotColor: $.colors("green", 500),
    changeRangeMin: 0,
    chartRangeMax: 10
  });
  $('.sparkline-compositeline').sparkline([4, 1, 5, 7, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 5, 6, 7], {
    composite: true,
    fillColor: false,
    height: '32px',
    width: '150px',
    lineColor: $.colors("red", 500),
    spotColor: $.colors("green", 500),
    minSpotColor: $.colors("primary", 500),
    maxSpotColor: $.colors("green", 500),
    changeRangeMin: 0,
    chartRangeMax: 10
  });

  // Line charts with normal range marker
  $('.sparkline-normalline').sparkline('html', {
    fillColor: false,
    height: '32px',
    width: '150px',
    lineColor: $.colors("red", 600),
    spotColor: $.colors("primary", 500),
    minSpotColor: $.colors("primary", 500),
    maxSpotColor: $.colors("primary", 500),
    normalRangeColor: $.colors("grey", 300),
    normalRangeMin: -1,
    normalRangeMax: 8
  });

  // Bar + line composite charts
  $('.sparkline-compositebar').sparkline('html', {
    type: 'bar',
    height: '32px',
    barWidth: 10,
    barSpacing: 5,
    barColor: $.colors("primary", 500)
  });

  $('.sparkline-compositebar').sparkline([4, 1, 5, 7, 9, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 2, 5, 6, 7], {
    composite: true,
    fillColor: false,
    lineColor: $.colors("red", 600),
    spotColor: $.colors("primary", 500)
  });

  // Discrete charts
  $('.sparkline-discrete1').sparkline('html', {
    type: 'discrete',
    height: '32px',
    lineColor: $.colors("primary", 500),
    xwidth: 36
  });

  $('.sparkline-discrete2').sparkline('html', {
    type: 'discrete',
    height: '32px',
    lineColor: $.colors("primary", 500),
    thresholdColor: $.colors("red", 600),
    thresholdValue: 4
  });

  // Bullet charts
  $('.sparkline-bullet').sparkline('html', {
    type: 'bullet',
    targetColor: $.colors("red", 500),
    targetWidth: '2',
    performanceColor: $.colors("primary", 600),
    rangeColors: [$.colors("primary", 100), $.colors("primary", 200), $.colors("primary", 400)]
  });

  // Customized line chart
  $('.sparkline-linecustom').sparkline('html', {
    height: '32px',
    width: '150px',
    lineColor: $.colors("red", 400),
    fillColor: $.colors("grey", 300),
    minSpotColor: false,
    maxSpotColor: false,
    spotColor: $.colors("green", 500),
    spotRadius: 2
  });

  // Tri-state charts using inline values
  $('.sparkline-tristate').sparkline('html', {
    type: 'tristate',
    height: '32px',
    barWidth: 10,
    barSpacing: 5,
    posBarColor: $.colors("primary", 500),
    negBarColor: $.colors("grey", 400),
    zeroBarColor: $.colors("red", 500)
  });

  $('.sparkline-tristatecols').sparkline('html', {
    type: 'tristate',
    height: '32px',
    barWidth: 10,
    barSpacing: 5,
    posBarColor: $.colors("primary", 500),
    negBarColor: $.colors("grey", 400),
    zeroBarColor: $.colors("red", 500),
    colorMap: {
      '-4': $.colors("red", 700),
      '-2': $.colors("primary", 600),
      '2': $.colors("grey", 500)
    }
  });

  // Box plots
  $('.sparkline-boxplot').sparkline('html', {
    type: 'box',
    height: '20px',
    width: '68px',
    lineColor: $.colors("primary", 700),
    boxLineColor: $.colors("primary", 400),
    boxFillColor: $.colors("primary", 400),
    whiskerColor: $.colors("grey", 600),
    // outlierLineColor: $.colors("grey", 400),
    // outlierFillColor: false,
    medianColor: $.colors("red", 500)
      // targetColor: $.colors("green", 500)
  });

  // Box plots raw
  $('.sparkline-boxplotraw').sparkline([1, 3, 5, 8, 10, 15, 18], {
    type: 'box',
    height: '20px',
    width: '78px',
    raw: true,
    showOutliers: true,
    target: 6,
    lineColor: $.colors("primary", 700),
    boxLineColor: $.colors("primary", 400),
    boxFillColor: $.colors("primary", 400),
    whiskerColor: $.colors("grey", 600),
    outlierLineColor: $.colors("grey", 400),
    outlierFillColor: $.colors("grey", 200),
    medianColor: $.colors("red", 500),
    targetColor: $.colors("green", 500)
  });

  // Pie charts
  $('.sparkline-pie').sparkline('html', {
    type: 'pie',
    height: '30px',
    sliceColors: [$.colors("primary", 500), $.colors("primary", 700), $.colors("primary", 600)]
  });

  $('.sparkline-pie-1').sparkline('html', {
    type: 'pie',
    height: '30px',
    sliceColors: [$.colors("primary", 500), $.colors("grey", 400)]
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppCalendar = App.extend({
    handleFullcalendar: function() {
      var my_events = [{
        title: 'All Day Event',
        start: '2016-10-01'
      }, {
        title: 'Long Event',
        start: '2016-10-07',
        end: '2016-10-10',
        backgroundColor: $.colors("cyan", 600),
        borderColor: $.colors("cyan", 600)
      }, {
        id: 999,
        title: 'Repeating Event',
        start: '2016-10-09T16:00:00',
        backgroundColor: $.colors("red", 600),
        borderColor: $.colors("red", 600)
      }, {
        title: 'Conference',
        start: '2016-10-11',
        end: '2016-10-13'
      }, {
        title: 'Meeting',
        start: '2016-10-12T10:30:00',
        end: '2016-10-12T12:30:00'
      }, {
        title: 'Lunch',
        start: '2016-10-12T12:00:00'
      }, {
        title: 'Meeting',
        start: '2016-10-12T14:30:00'
      }, {
        title: 'Happy Hour',
        start: '2016-10-12T17:30:00'
      }, {
        title: 'Dinner',
        start: '2016-10-12T20:00:00'
      }, {
        title: 'Birthday Party',
        start: '2016-10-13T07:00:00'
      }];
      var actionBtn = $('.site-action').actionBtn().data('actionBtn');
      var my_options = {
        header: {
          left: null,
          center: 'prev,title,next',
          right: 'month,agendaWeek,agendaDay'
        },
        defaultDate: '2016-10-12',
        selectable: true,
        selectHelper: true,
        select: function() {
          $('#addNewEvent').modal('show');
        },
        editable: true,
        eventLimit: true,
        windowResize: function(view) {
          var width = $(window).outerWidth();
          var options = $.extend({}, my_options);
          options.events = view.calendar.getEventCache();
          options.aspectRatio = width < 667 ? 0.5 : 1.35;

          $('#calendar').fullCalendar('destroy');
          $('#calendar').fullCalendar(options);
        },
        eventClick: function(event) {
          var color = event.backgroundColor ? event.backgroundColor : $.colors('blue', 600);
          $('#editEname').val(event.title);

          if (event.start) {
            $('#editStarts').datepicker('update', event.start._d);
          } else {
            $('#editStarts').datepicker('update', '');
          }
          if (event.end) {
            $('#editEnds').datepicker('update', event.end._d);
          } else {
            $('#editEnds').datepicker('update', '');
          }

          $('#editColor [type=radio]').each(function() {
            var $this = $(this),
              value = $this.data('color').split('|'),
              value = $.colors(value[0], value[1]);
            if (value === color) {
              $this.prop('checked', true);
            } else {
              $this.prop('checked', false);
            }
          });
          $('#editColor [value=' + event.backgroundColor + ']').prop('checked', true);

          $('#editNewEvent').modal('show').one('hidden.bs.modal', function(e) {
            event.title = $('#editEname').val();

            var color = $('#editColor [type=radio]:checked').data('color').split('|');
            color = $.colors(color[0], color[1]);
            event.backgroundColor = color;
            event.borderColor = color;

            event.start = new Date($('#editStarts').data('datepicker').getDate());
            event.end = new Date($('#editEnds').data('datepicker').getDate());
            $('#calendar').fullCalendar('updateEvent', event);
          })
        },
        eventDragStart: function() {
          actionBtn.show();
        },
        eventDragStop: function() {
          actionBtn.hide();
        },
        events: my_events,
        droppable: true
      };

      var _options;
      var my_options_mobile = $.extend({}, my_options);

      my_options_mobile.aspectRatio = 0.5;
      _options = $(window).outerWidth() < 667 ? my_options_mobile : my_options;

      $('#editNewEvent').modal();
      $('#calendar').fullCalendar(_options);
    },

    handleSelective: function() {
      var member = [{
        id: 'uid_1',
        name: 'Herman Beck',
        avatar: '../../../../global/portraits/1.jpg'
      }, {
        id: 'uid_2',
        name: 'Mary Adams',
        avatar: '../../../../global/portraits/2.jpg'
      }, {
        id: 'uid_3',
        name: 'Caleb Richards',
        avatar: '../../../../global/portraits/3.jpg'
      }, {
        id: 'uid_4',
        name: 'June Lane',
        avatar: '../../../../global/portraits/4.jpg'
      }];

      var items = [{
        id: 'uid_1',
        name: 'Herman Beck',
        avatar: '../../../../global/portraits/1.jpg'
      }, {
        id: 'uid_2',
        name: 'Caleb Richards',
        avatar: '../../../../global/portraits/2.jpg'
      }];

      $('[data-plugin="jquery-selective"]').selective({
        namespace: 'addMember',
        local: member,
        selected: items,
        buildFromHtml: false,
        tpl: {
          optionValue: function(data) {
            return data.id;
          },
          frame: function() {
            return '<div class="' + this.namespace + '">' +
              this.options.tpl.items.call(this) +
              '<div class="' + this.namespace + '-trigger">' +
              this.options.tpl.triggerButton.call(this) +
              '<div class="' + this.namespace + '-trigger-dropdown">' +
              this.options.tpl.list.call(this) +
              '</div>' +
              '</div>' +
              '</div>'
          },
          triggerButton: function() {
            return '<div class="' + this.namespace + '-trigger-button"><i class="md-plus"></i></div>';
          },
          listItem: function(data) {
            return '<li class="' + this.namespace + '-list-item"><img class="avatar" src="' + data.avatar + '">' + data.name + '</li>';
          },
          item: function(data) {
            return '<li class="' + this.namespace + '-item"><img class="avatar" src="' + data.avatar + '" title="' + data.name + '">' +
              this.options.tpl.itemRemove.call(this) +
              '</li>';
          },
          itemRemove: function() {
            return '<span class="' + this.namespace + '-remove"><i class="md-minus-circle"></i></span>';
          },
          option: function(data) {
            return '<option value="' + this.options.tpl.optionValue.call(this, data) + '">' + data.name + '</option>';
          }
        }
      });
    },

    handleAction: function() {
      var actionBtn = $('.site-action').actionBtn().data('actionBtn');
    },

    handleEventList: function() {
      $('#addNewEventBtn').on('click', function() {
        $('#addNewEvent').modal('show');
      });

      $('.calendar-list .calendar-event').each(function() {
        var $this = $(this),
          color = $this.data('color').split('-');
        $this.data('event', {
          title: $this.data('title'),
          stick: $this.data('stick'),
          backgroundColor: $.colors(color[0], color[1]),
          borderColor: $.colors(color[0], color[1])
        });
        $this.draggable({
          zIndex: 999,
          revert: true,
          revertDuration: 0,
          helper: function() {
            return '<a class="fc-day-grid-event fc-event fc-start fc-end" style="background-color:' + $.colors(color[0], color[1]) + ';border-color:' + $.colors(color[0], color[1]) + '">' +
              '<div class="fc-content">' +
              '<span class="fc-title">' + $this.data('title') + '</span>' +
              '</div>' +
              '</a>';
          }
        });
      });
    },

    handleListItem: function() {
      $('.site-action-toggle').on('click', function(e) {
        $('#addNewCalendar').modal('show');
        e.stopPropagation();
      });

      $(document).on('click', '[data-tag=list-delete]', function(e) {
        bootbox.dialog({
          message: "Do you want to delete the calendar?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                // $(e.target).closest('.list-group-item').remove();
              }
            }
          }
        });
      });
    },

    run: function(next) {
      $('#addNewCalendarForm').modal({
        show: false
      });

      $('#addNewEvent').modal({
        show: false
      });

      $('#editNewEvent').modal({
        show: false
      });


      this.handleEventList();
      this.handleFullcalendar();
      this.handleAction();
      this.handleSelective();
      this.handleListItem();

      next();
    }
  });

  $(document).ready(function() {
    AppCalendar.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppContacts = App.extend({
    handleAction: function() {

      var actionBtn = $('.site-action').actionBtn().data('actionBtn');
      var $selectable = $('[data-selectable]');

      $('.site-action-toggle', '.site-action').on('click', function(e) {
        var $selected = $selectable.asSelectable('getSelected');

        if ($selected.length === 0) {
          $('#addUserForm').modal('show');
          e.stopPropagation();
        }
      });

      $('[data-action="trash"]', '.site-action').on('click', function() {
        console.log('trash');
      });

      $('[data-action="folder"]', '.site-action').on('click', function() {
        console.log('folder');
      });

      $selectable.on('asSelectable::change', function(e, api, checked) {
        if (checked) {
          actionBtn.show();
        } else {
          actionBtn.hide();
        }
      });
    },

    handleEdit: function() {
      $(document).on('click', '[data-toggle=edit]', function() {
        var $button = $(this),
          $panel = $button.parents('.slidePanel'),
          $form = $panel.find('.user-info');

        $button.toggleClass('active');
        $form.toggleClass('active');
      });

      $(document).on('slidePanel::afterLoad', function(e, api) {
        $.components.init('material', api.$panel);
      });

      $(document).on('change', '.user-info .form-group', function(e) {
        var $input = $(this).find('input'),
          $span = $(this).siblings('span');
        $span.html($input.val());
      });

    },

    handleListItem: function() {
      $('#addLabelToggle').on('click', function(e) {
        $('#addLabelForm').modal('show');
        e.stopPropagation();
      });

      $(document).on('click', '[data-tag=list-delete]', function(e) {
        bootbox.dialog({
          message: "Do you want to delete the contact?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                // $(e.target).closest('.list-group-item').remove();
              }
            }
          }
        });
      });
    },

    run: function(next) {
      this.handleAction();
      this.handleEdit();
      this.handleListItem();

      $('#addlabelForm').modal({
        show: false
      });

      $('#addUserForm').modal({
        show: false
      });

      next();
    }
  });

  $(document).ready(function() {
    AppContacts.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  window.AppDocuments = App.extend({
    affixHandle: function() {
      $('#articleAffix').affix({
        offset: {
          top: 210
        }
      });
    },
    scrollHandle: function() {
      $('body').scrollspy({
        target: '#articleAffix'
      });
    },
    run: function(next) {
      this.scrollHandle();
      this.affixHandle();

      next();
    }
  });

  $(document).ready(function() {
    AppDocuments.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppForum = App.extend({
    run: function(next) {
      next();
    }
  });

  $(document).ready(function() {
    AppForum.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppLocation = App.extend({
    handleMap: function() {

      var self = this;
      /* set default map height */
      var navbarH = $(".site-navbar").outerHeight();
      var footerH = $(".site-footer").outerHeight();
      var menubarH = $(".site-menubar").outerHeight();
      var mapH = $(window).height() - navbarH - footerH - menubarH;

      $(".page-main").outerHeight(mapH);

      var mapLatlng = L.latLng(37.769, -122.446);
      var $allFriends = $(".app-location .friend-info");
      $allFriends.each(function() {
        self.allFriends.push($(this));
      });

      // this accessToken, you can get it to here ==> [ https://www.mapbox.com ]
      L.mapbox.accessToken = 'pk.eyJ1IjoiYW1hemluZ3N1cmdlIiwiYSI6ImNpaDVubzBoOTAxZG11dGx4OW5hODl2b3YifQ.qudwERFDdMJhFA-B2uO6Rg';

      /* format map */
      var map = L.mapbox.map('map', 'mapbox.light').setView(mapLatlng, 18);

      /* get all friend's ID input [allFriendId] array */
      $allFriends.each(function() {
        self.allFriendId.push($(this).attr("data-friend-id"));
      });
      /* add markercluster Plugin */
      // this mapbox's Plugins,you can get it to here ==> [ https://github.com/Leaflet/Leaflet.markercluster.git ]
      var markers = new L.MarkerClusterGroup({
        removeOutsideVisibleBounds: false,
        polygonOptions: {
          color: "#444"
        }
      });




      function addMarker(allFriendId, allFriends) {

        for (var i = 0; i < allFriends.length; i++) {
          var x, path;
          if (i % 2 === 0) {
            x = 1 * Math.random();
          } else {
            x = -1 * Math.random();
          }

          var markerLatlng = L.latLng(37.769 + Math.random() / 170 * x, -122.446 + Math.random() / 150 * x);

          path = allFriends[i].find("img").attr("src")

          var divContent = "<div class='in-map-markers'><div class='friend-icon'><img src='" + path + "' alt='" + allFriendId[i] + "' /></div></div>"

          var friendImg = L.divIcon({
            html: divContent,
            iconAnchor: [0, 0],
            className: ""
          })

          /* create new marker and add to map */
          var popupInfo = "<div class='friend-popup-info'><div class='detail'>info</div><h3>" + allFriends[i].find(".friend-name").html() + "</h3><p>" + allFriends[i].find(".friend-title").html() + "</p></div><i class='icon md-chevron-right'></i>";

          var marker = L.marker(markerLatlng, {
            alt: allFriendId[i],
            title: $allFriends[i].getElementsByClassName("friend-name")[0].innerHTML,
            icon: friendImg
          }).bindPopup(popupInfo, {
            closeButton: false
          });

          markers.addLayer(marker);

          self.allMarkers.push(marker);
          marker.on("popupopen", function() {
            this._icon.className += " marker-active";
            this.setZIndexOffset(999);
          });

          marker.on("popupclose", function() {
            if (this._icon) {
              this._icon.className = "leaflet-marker-icon leaflet-zoom-animated leaflet-clickable";
            }
            this.setZIndexOffset(450);
          });
        }

        map.addLayer(markers);
      }

      /* Add markers to map */
      addMarker(self.allFriendId, self.allFriends);

      /* when map idle,run [getInMapMarkers] Function */
      map.once("ready", getInMapMarkers);

      map.on("viewreset", getInMapMarkers);
      map.on("moveend", getInMapMarkers);

      this.handleClickAction(map);

      function getInMapMarkers() {
        var inMapMarkersId = [],
          listFriends = [];

        /* Get the object of all Markers in the map view */
        for (var i = 0; i < self.allMarkers.length; i++) {
          if (map.getBounds().contains(self.allMarkers[i].getLatLng())) {
            inMapMarkersId.push(self.allMarkers[i].options.alt);
          }
        }

        /* show friend in the aside list */
        for (var i = 0; i < self.allFriends.length; i++) {
          if (inMapMarkersId.indexOf(self.allFriends[i].attr("data-friend-id")) === -1) {
            self.allFriends[i].hide();
          } else {
            self.allFriends[i].show();
          }
        }

        for (var i = 0; i < inMapMarkersId.length; i++) {
          $(".app-location .friend-info").each(function() {
            if ($(this).attr("data-friend-id") === inMapMarkersId[i])
              listFriends.push($(this));
          });
        }
        self.inListFriends = listFriends;
      }
    },
    handleFind: function() {
      var self = this;

      $(".search-friends input").on("focus", function() {
        $(this).on("keyup", function() {
          var inputName = $(".search-friends input").val();

          for (var i = 0; i < self.inListFriends.length; i++) {

            /* Gets the text for each of the input boxes */
            var friendName = self.inListFriends[i].find(".friend-name").html();

            if (inputName.length <= friendName.length) {
              for (var j = 1; j <= inputName.length; j++) {
                if (inputName.substring(0, j).toLowerCase() === friendName.substring(0, j).toLowerCase()) {
                  self.inListFriends[i].show();
                } else {
                  self.inListFriends[i].hide();
                }
              }
            } else {
              self.inListFriends[i].hide();
            }
          }
          if (inputName === "")
            for (var i = 0; i < self.inListFriends.length; i++) {
              self.inListFriends[i].show();
            }
        });

      });
      $(".search-friends input").on("focusout", function() {
        $(this).off("keyup");
      })
    },
    handleResize: function() {
      $(window).on("resize", function() {
        var navbarH = $(".site-navbar").outerHeight();
        var footerH = $(".site-footer").outerHeight();
        var mapH = $(window).height() - navbarH - footerH;

        $(".page-main").outerHeight(mapH);
      });
    },
    handleClickAction: function(map) {
      var self = this;

      $(".app-location .page-aside .list-group").on("click", ".widget-content", function() {

        var thisId = $(this).parents(".friend-info").attr("data-friend-id");
        //stop Bubble
        $(".list-inline").on("click", function(event) {
          event.stopPropagation();
        });

        for (var i = 0; i < self.allMarkers.length; i++) {
          if (self.allMarkers[i].options.alt === thisId) {
            map.panTo(self.allMarkers[i].getLatLng());
            self.allMarkers[i].openPopup();
          }
        }
      });
    },
    run: function() {
      this.allFriends = [];
      this.allFriendId = [];
      this.allMarkers = [];
      this.inListFriends = [];

      this.handleMap();
      this.handleResize();
      this.handleFind();
    }
  });

  $(document).ready(function($) {
    AppLocation.run();
  })
}(document, window, jQuery));

(function(document, window, $) {
  'use strict';

  window.AppMailbox = App.extend({
    handleAction: function() {
      var actionBtn = $('.site-action').actionBtn().data('actionBtn');
      var $selectable = $('[data-selectable]');

      $('.site-action-toggle', '.site-action').on('click', function(e) {
        var $selected = $selectable.asSelectable('getSelected');

        if ($selected.length === 0) {
          $('#addMailForm').modal('show');
          e.stopPropagation();
        }
      });

      $('[data-action="trash"]', '.site-action').on('click', function() {
        console.log('trash');
      });

      $('[data-action="inbox"]', '.site-action').on('click', function() {
        console.log('folder');
      });

      $selectable.on('asSelectable::change', function(e, api, checked) {
        if (checked) {
          actionBtn.show();
        } else {
          actionBtn.hide();
        }
      });
    },

    handleListItem: function() {
      $('#addLabelToggle').on('click', function(e) {
        $('#addLabelForm').modal('show');
        e.stopPropagation();
      });

      $(document).on('click', '[data-tag=list-delete]', function(e) {
        bootbox.dialog({
          message: "Do you want to delete the label?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                // $(e.target).closest('.list-group-item').remove();
              }
            }
          }
        });
      });
    },


    itemTpl: function(data) {
      return '<tr id="' + data.id + '" data-mailbox="slidePanel" ' + (data.unread === 'true' ? 'class="unread"' : '') + '>' +
        '<td class="cell-60">' +
        '<span class="checkbox-custom checkbox-primary checkbox-lg">' +
        '<input type="checkbox" class="mailbox-checkbox selectable-item" id="mail_' + data.id + '"/>' +
        '<label for="mail_' + data.id + '"></label>' +
        '</span>' +
        '</td>' +
        '<td class="cell-30 responsive-hide">' +
        '<span class="checkbox-important checkbox-default">' +
        '<input type="checkbox" class="mailbox-checkbox mailbox-important" ' + (data.starred === 'true' ? 'checked="checked"' : '') + ' id="mail_' + data.id + '_important"/>' +
        '<label for="mail_' + data.id + '_important"></label>' +
        '</span>' +
        '</td>' +
        '<td class="cell-60 responsive-hide">' +
        '<a class="avatar" href="javascript:void(0)"><img class="img-responsive" src="' + data.avatar + '" alt="..."></a>' +
        '</td>' +
        '<td>' +
        '<div class="content">' +
        '<div class="title">' + data.name + '</div>' +
        '<div class="abstract">' + data.title + '</div>' +
        '</div>' +
        '</td>' +
        '<td class="cell-30 responsive-hide">' +
        (data.attachments.length > 0 ? '<i class="icon md-attachment-alt" aria-hidden="true"></i>' : '') +
        '</td>' +
        '<td class="cell-130">' +
        '<div class="time">' + data.time + '</div>' +
        (data.group.length > 0 ? '<div class="identity"><i class="md-circle ' + data.color + '" aria-hidden="true"></i>' + data.group + '</div>' : '') +
        '</td>' +
        '</tr>';
    },

    attachmentsTpl: function(data) {
      var self = this,
        html = '';

      html += '<div class="mail-attachments">' +
        '<p><i Class="icon md-attachment-alt"></i>Attachments | <a href="javascript:void(0)">Download All</a></p>' +
        '<ul class="list-group">';

      $.each(data, function(n, item) {
        html += self.attachmentTpl(item);
      });

      html += '</ul></div>';

      return html;
    },

    attachmentTpl: function(data) {
      return '<li class="list-group-item">' +
        '<span class="name">' + data.name + '</span><span class="size">' + data.size + '</span>' +
        '<button type="button" class="btn btn-icon btn-pure btn-default"><i class="icon md-download" aria-hidden="true"></i></button>' +
        '</li>';
    },

    messagesTpl: function(data) {
      var self = this,
        html = '';

      $.each(data.messages, function(n, item) {
        html += '<section class="slidePanel-inner-section">' +
          '<div class="mail-header">' +
          '<div class="mail-header-main">' +
          '<a class="avatar" href="javascript:void(0)"><img src="' + data.avatar + '" alt="..."></a>' +
          '<div><span class="name">' + data.name + '</span></div>' +
          '<div>' +
          '<a href="javascript:void(0)" class="mailbox-panel-email">' + data.email + '</a>' +
          ' to <a href="javascript:void(0)" class="margin-right-10">me</a>' +
          '<span class="identity"><i class="md-circle red-600" aria-hidden="true"></i>' + data.group + '</span>' +
          '</div>' +
          '</div>' +
          '<div class="mail-header-right">' +
          '<span class="time">' + item.time + '</span>' +
          '<div class="btn-group btn-group-flat actions" role="group">' +
          '<button type="button" class="btn btn-icon btn-pure btn-default"><i class="icon md-star" aria-hidden="true"></i></button>' +
          '<button type="button" class="btn btn-icon btn-pure btn-default"><i class="icon md-mail-reply" aria-hidden="true"></i></button>' +
          '</div>' +
          '</div>' +
          '</div>' +
          '<div class="mail-content">' + item.content + '</div>';

        if (n === 0) {
          if (item.attachments && item.attachments.length > 0) {
            html += this.attachmentsTpl(item.attachments);
          }
        }

        html += '</section>';
      });

      return html;
    },

    initMail: function() {
      var self = this;

      $.getJSON('../../../assets/data/appsMailbox.json', function(data) {
        var $wrap = $('#mailboxTable');

        self.buildMail($wrap, data);
        self.initMailData(data);
        self.handlSlidePanelPlugin();
      });
    },

    initMailData: function(data) {
      this.mailboxData = data;
    },

    buildMail: function($wrap, data) {
      var self = this,
        $tbody = $('<tbody></tbody>');

      $.each(data, function(i, item) {
        self.buildItem($tbody, item);
      });

      $wrap.empty().append($tbody);
    },

    buildItem: function($wrap, data) {
      $wrap.append($(this.itemTpl(data)).data('mailInfo', data));
    },

    buildPanel: function() {

    },

    filter: function(flag, value) {

    },

    handlePanel: function() {
      $(document).on('click', '[data-mailbox="slidePanel"]', function(e) {
        console.log(this, $(this))
      });
    },

    handlSlidePanelPlugin: function() {
      if (typeof $.slidePanel === 'undefined') return;

      var self = this;
      var defaults = $.components.getDefaults("slidePanel");
      var options = $.extend({}, defaults, {
        template: function(options) {
          return '<div class="' + options.classes.base + ' ' + options.classes.base + '-' + options.direction + '">' +
            '<div class="' + options.classes.base + '-scrollable"><div>' +
            '<div class="' + options.classes.content + '"></div>' +
            '</div></div>' +
            '<div class="' + options.classes.base + '-handler"></div>' +
            '</div>';
        },
        afterLoad: function(object) {
          var _this = this,
            $target = $(object.target),
            info = $target.data('taskInfo');

          this.$panel.find('.' + this.options.classes.base + '-scrollable').asScrollable({
            namespace: 'scrollable',
            contentSelector: '>',
            containerSelector: '>'
          });
        },
        contentFilter: function(data, object) {
          var $target = $(object.target),
            info = $target.data('mailInfo'),
            $panel = $(data);

          $('.mailbox-panel-title', $panel).html(info.title);

          $('.slidePanel-messages', $panel).html(self.messagesTpl(info));

          return $panel;
        }
      });

      $(document).on('click', '[data-mailbox="slidePanel"]', function(e) {
        $.slidePanel.show({
          url: 'panel.tpl',
          target: $(this)
        }, options);

        e.stopPropagation();
      });
    },


    run: function(next) {
      this.handleAction();
      this.handleListItem();

      this.initMail();

      $('#addlabelForm').modal({
        show: false
      });

      $('#addMailForm').modal({
        show: false
      });

      $('.checkbox-important').on('click', function(e) {
        e.stopPropagation();
      });

      this.handleMultiSelect();
      next();
    }
  });

  $(document).ready(function() {
    AppMailbox.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppMedia = App.extend({
    handleArrangement: function() {
      $('#arrangement-grid').on('click', function() {
        var $this = $(this);
        if ($this.hasClass('active')) {
          return;
        }
        $('#arrangement-list').removeClass('active');
        $this.addClass('active');
        $('.media-list').removeClass('is-list').addClass('is-grid');
        $('.media-list>ul>li').removeClass('animation-fade').addClass('animation-scale-up');

      });

      $('#arrangement-list').on('click', function() {
        var $this = $(this);
        if ($this.hasClass('active')) {
          return;
        }
        $('#arrangement-grid').removeClass('active');
        $this.addClass('active');
        $('.media-list').removeClass('is-grid').addClass('is-list');
        $('.media-list>ul>li').removeClass('animation-scale-up').addClass('animation-fade');
      });
    },

    handleActive: function() {
      $.components.getDefaults('selectable').rowSelector = '.media-item';
    },

    handleAction: function() {
      var actionBtn = $('.site-action').actionBtn().data('actionBtn');
      var $selectable = $('[data-selectable]');

      $('.site-action-toggle', '.site-action').on('click', function(e) {
        var $selected = $selectable.asSelectable('getSelected');

        if ($selected.length === 0) {
          $('#fileupload').trigger('click');
          e.stopPropagation();
        }
      });

      $('[data-action="trash"]', '.site-action').on('click', function() {
        console.log('trash');
      });

      $('[data-action="download"]', '.site-action').on('click', function() {
        console.log('download');
      });

      $selectable.on('asSelectable::change', function(e, api, checked) {
        if (checked) {
          actionBtn.show();
        } else {
          actionBtn.hide();
        }
      });
    },

    handleDropdownAction: function() {
      $('.info-wrap>.dropdown').on('show.bs.dropdown', function() {
        $(this).closest('.media-item').toggleClass('item-active');
      }).on('hidden.bs.dropdown', function() {
        $(this).closest('.media-item').toggleClass('item-active');
      });
      $('.info-wrap .dropdown-menu').on('click', function(e) {
        e.stopPropagation();
      });

    },

    run: function() {
      $('.media-item-actions').on('click', function(e) {
        e.stopPropagation();
      });

      this.handleArrangement();
      this.handleAction();
      this.handleActive();
      this.handleDropdownAction();
    }
  });

  $(document).ready(function() {
    AppMedia.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppMessage = App.extend({
    scrollChatsToBottom: function() {
      var $chatsWrap = $(".app-message-chats");
      var chatsWrapH = $chatsWrap.height();
      var chatsH = $(".chats", $chatsWrap).outerHeight();
      var historyBtnH = $("#historyBtn").outerHeight();

      $chatsWrap.scrollTop(chatsH + historyBtnH - chatsWrapH);
    },

    handleResize: function() {
      var self = this;

      $(window).on("resize", function() {
        self.scrollChatsToBottom();
      });
    },

    handleTalking: function() {
      var self = this;
      var $chatsWrap = $(".app-message-chats");
      var $textarea = $('.message-input textarea');
      var $textareaWrap = $('.app-message-input');

      autosize($('.message-input textarea'));

      $textarea.on('autosize:resized', function() {
        var height = $textareaWrap.outerHeight();
        $chatsWrap.css('height', 'calc(100% - ' + height + 'px)');
        self.scrollChatsToBottom();
      });


      $(".message-input-btn>button").on("click", function() {
        var talkContents = $(".message-input>.form-control").val();
        var $newMsg = $(
          "<div class='chat-content'>" +
          "<p>" + talkContents + "</p>" +
          "</div>"
        );

        if (talkContents.length > 0) {
          $(".chat").last().find(".chat-body").append($newMsg);
          $(".message-input>.form-control").attr("placeholder", "");
          $(".message-input>.form-control").val("");
        } else {
          $(".message-input>.form-control").attr("placeholder", "type text here...");
        }

        $(".message-input>.form-control").focus();

        self.scrollChatsToBottom();
      });
    },
    run: function(next) {
      this.scrollChatsToBottom();
      this.handleResize();
      this.handleTalking();
    }
  });

  $(document).ready(function($) {
    AppMessage.run();
  })
}(document, window, jQuery));

(function(document, window, $) {
  'use strict';

  window.AppNoteBook = App.extend({
    handleAction: function() {
      var $actionBtn = $('.site-action').actionBtn({
        toggleSelector: '.list-group-item',
        listSelector: '.site-action-buttons'
      }).data("actionBtn");

      var $noteList = $(".list-group-item");

      $('.site-action-toggle').on("click", function(e) {
        if (!$noteList.hasClass("active")) {
          $('#addNewNote').modal('show');

          e.stopPropagation();
        }
      })

      $noteList.on("click", function() {
        $(this).siblings().removeClass("active");
        $(this).addClass("active");

        if ($(this).hasClass("active")) {
          $actionBtn.show();

          $(".site-action-toggle").on("click", function() {
            $(".list-group-item").removeClass("active");
            $actionBtn.hide();
          });
        }
      });
    },
    handleEdit: function() {
      $("#mdEdit").markdown({
        autofocus: false,
        savable: false
      });
    },
    handleResize: function() {
      var self = this;

      $(window).on("resize", function() {
        self.handleEdit();
      });
    },
    run: function(next) {
      this.handleAction();
      this.handleEdit();
    }
  });

  $(document).ready(function($) {
    AppNoteBook.run();
  })
}(document, window, jQuery));

(function(document, window, $) {
  'use strict';

  window.AppProjects = App.extend({
    handleSelective: function() {
      var members = [{
          id: 'uid_1',
          name: 'Herman Beck',
          img: '../../../../global/portraits/1.jpg'
        }, {
          id: 'uid_2',
          name: 'Mary Adams',
          img: '../../../../global/portraits/2.jpg'
        }, {
          id: 'uid_3',
          name: 'Caleb Richards',
          img: '../../../../global/portraits/3.jpg'
        }, {
          id: 'uid_4',
          name: 'June Lane',
          img: '../../../../global/portraits/4.jpg'
        }],
        selected = [{
          id: 'uid_1',
          name: 'Herman Beck',
          img: '../../../../global/portraits/1.jpg'
        }, {
          id: 'uid_2',
          name: 'Caleb Richards',
          img: '../../../../global/portraits/2.jpg'
        }];

      $('[data-plugin="jquery-selective"]').selective({
        namespace: 'addMember',
        local: members,
        selected: selected,
        buildFromHtml: false,
        tpl: {
          optionValue: function(data) {
            return data.id;
          },
          frame: function() {
            return '<div class="' + this.namespace + '">' +
              this.options.tpl.items.call(this) +
              '<div class="' + this.namespace + '-trigger">' +
              this.options.tpl.triggerButton.call(this) +
              '<div class="' + this.namespace + '-trigger-dropdown">' +
              this.options.tpl.list.call(this) +
              '</div>' +
              '</div>' +
              '</div>'
          },
          triggerButton: function() {
            return '<div class="' + this.namespace + '-trigger-button"><i class="md-plus"></i></div>';
          },
          listItem: function(data) {
            return '<li class="' + this.namespace + '-list-item"><img class="avatar" src="' + data.img + '">' + data.name + '</li>';
          },
          item: function(data) {
            return '<li class="' + this.namespace + '-item"><img class="avatar" src="' + data.img + '">' +
              this.options.tpl.itemRemove.call(this) +
              '</li>';
          },
          itemRemove: function() {
            return '<span class="' + this.namespace + '-remove"><i class="md-minus-circle"></i></span>';
          },
          option: function(data) {
            return '<option value="' + this.options.tpl.optionValue.call(this, data) + '">' + data.name + '</option>';
          }
        }
      });
    },

    handleProject: function() {
      $(document).on('click', '[data-tag=project-delete]', function(e) {
        bootbox.dialog({
          message: "Do you want to delete the project?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                // $(e.target).closest('.list-group-item').remove();
              }
            }
          }
        });
      });
    },

    run: function(next) {
      this.handleSelective();
      this.handleProject();

      next();
    }
  });

  $(document).ready(function() {
    AppProjects.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppTaskboard = App.extend({
    //TPL
    stageTpl: function(title) {
      return '<li class="taskboard-stage">' +
        '<header class="taskboard-stage-header">' +
        '<div class="taskboard-stage-actions pull-right">' +
        '<div class="dropdown">' +
        '<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"><i class="icon md-chevron-down" aria-hidden="true"></i></a>' +
        '<ul class="dropdown-menu bullet" role="menu">' +
        '<li role="presentation" class="taskboard-stage-rename"><a href="javascript:void(0)" role="menuitem"><i class="icon md-edit" aria-hidden="true"></i>Rename</a></li>' +
        '<li role="presentation" class="taskboard-stage-delete" ><a href="javascript:void(0)" role="menuitem"><i class="icon md-delete" aria-hidden="true"></i>Delete</a></li>' +
        '<li class="taskboard-stage-rename-wrap">' +
        '<div class="form-group">' +
        '<input class="form-control taskboard-stage-rename-input" type="text" value="' + title + '" name="name">' +
        '</div>' +
        '<button class="btn btn-primary btn-block taskboard-stage-rename-save" type="button">Save</button>' +
        '</li>' +
        '</ul>' +
        '</div>' +
        '</div>' +
        '<h5 class="taskboard-stage-title">' + title + '</h5>' +
        '</header>' +
        '<div class="taskboard-stage-content">' +
        '<ul class="list-group taskboard-list">' +
        '</ul>' +
        '<div class="action-wrap">' +
        '<a class="add-item-toggle" href="#"><i class="icon md-plus" aria-hidden="true"></i>Add Task</a>' +
        '<div class="add-item-wrap">' +
        '<form class="add-item" role="form" method="post" action="#">' +
        '<div class="form-group">' +
        '<label class="control-label margin-bottom-15" for="name">Task name:</label>' +
        '<input class="form-control" type="text" placeholder="Task name" name="name">' +
        '</div>' +
        '<div class="form-group text-right">' +
        '<a class="btn btn-sm btn-white btn-flat add-item-cancel">Cancel</a>' +
        '<button type="button" class="btn btn-primary add-item-add">Add</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</li>';
    },

    taskTpl: function(data) {
      return '<li class="list-group-item priority-' + data.priority + '" data-taskboard="slidePanel" data-url="panel.tpl">' +
        '<div class="checkbox-custom checkbox-primary">' +
        '<input type="checkbox" ' + (data.complete ? 'checked="checked"' : '') + ' name="checkbox">' +
        '<label class="task-title">' + data.title + '</label>' +
        '</div>' +
        '<div class="task-badges"></div>' +
        '<ul class="task-members">' +
        '<li><img class="avatar avatar-sm" src="../../../../global/portraits/5.jpg"></li>' +
        '</div>' +
        '</li>';
    },

    badgesTpl: function(type, content) {
      var html = '';
      switch (type) {
        case 'duedate':
          html = '<span class="task-badge task-badge-subtask icon md-calendar">' + content + '</span>';
          break;
        case 'subtasks':
          html = '<span class="task-badge task-badge-subtask icon md-format-list-bulleted">' + content + '</span>';
          break;
        case 'attachments':
          html = '<span class="task-badge task-badge-attachments icon md-attachment-alt">' + content + '</span>';
          break;
        case 'comments':
          html = '<span class="task-badge task-badge-comments icon md-comment">' + content + '</span>';
          break;
      }
      return html;
    },

    membersTpl: function(src) {
      return '<li><img class="avatar avatar-sm" src="' + src + '"></li>';
    },

    subtaskTpl: function(data) {
      return '<li class="list-group-item subtask">' +
        '<div class="checkbox-custom checkbox-primary">' +
        '<input type="checkbox" ' + (data.complete ? 'checked="checked"' : '') + ' name="checkbox">' +
        '<label class="title">' + data.title + '</label>' +
        '</div>' +
        '<div class="subtask-editor">' +
        '<form>' +
        '<div class="form-group">' +
        '<input class="form-control subtask-title" type="text" name="title">' +
        '</div>' +
        '<div class="form-group">' +
        '<button class="btn btn-primary subtask-editor-save" type="button">Save</button>' +
        '<a class="btn btn-sm btn-white subtask-editor-delete" href="javascript:void(0)">Delete</a>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</li>';
    },

    attachmentTpl: function(data) {
      return '<li class="list-group-item">' +
        '<div class="meida">' +
        '<div class="media-left">' +
        '<div class="attachments-image">' +
        '<img src="' + data.src + '">' +
        '</div>' +
        '</div>' +
        '<div class="media-body">' +
        '<p><span class="name">' + data.title + '</span><span</p>' +
        '<p>' +
        '<span class="size">' + data.size + '</span>' +
        '<span class="attachments-actions">' +
        '<button class="btn btn-icon btn-pure" type="button">' +
        '<i class="icon md-download" aria-hidden="true"></i>' +
        '</button>' +
        '<button class="btn btn-icon btn-pure" type="button">' +
        '<i class="icon md-delete" aria-hidden="true"></i>' +
        '</button>' +
        '</span>' +
        '</p>' +
        '</div>' +
        '</div>' +
        '</li>';
    },

    commentTpl: function(src, user, time, content) {
      return '<div class="comment media">' +
        '<div class="media-left">' +
        '<a class="avatar avatar-lg" href="javascript:void(0)">' +
        '<img src="' + src + '" alt="...">' +
        '</a>' +
        '</div>' +
        '<div class="media-body">' +
        '<div class="comment-body">' +
        '<a class="comment-author" href="javascript:void(0)">' + user + '</a>' +
        '<div class="comment-meta">' +
        '<span class="date">' + time + '</span>' +
        '</div>' +
        '<div class="comment-content"><p>' + content + '</p></div>' +
        '</div>' +
        '</div>' +
        '</div>';
    },

    dataTpl: function() {
      var data = {
        "status": false,
        "title": "",
        "description": "",
        "priority": "normal",
        "duedate": "",
        "members": [],
        "subtasks": [],
        "attachments": [],
        "comments": []
      };
      return data;
    },

    //Init Page
    init: function() {
      var self = this;
      $.getJSON('../../../assets/data/taskboard.json', function(data) {
        var $wrap = $('#taskboard-stages');
        self.buildStage($wrap, data);
        self.initSortable();
      });
    },

    buildStage: function($wrap, data) {
      if (data.length === 0) return;

      var self = this;
      $.each(data, function(n, info) {
        var $stage = $(self.stageTpl(info.title, info.type));
        self.buildTask($stage, info.tasks);
        $wrap.append($stage);
      });
    },

    buildTask: function($wrap, data, once) {
      if (data.length === 0) return;

      var self = this,
        $container = $('.taskboard-list', $wrap);
      if (once) {
        var $task = $(self.taskTpl(data));
        self.buildBadges($task, data);
        $task.data('taskInfo', data);
        $wrap.append($task);
      } else {
        $.each(data, function(n, info) {
          var $task = $(self.taskTpl(info));
          self.buildBadges($task, info);
          self.buildMembers($task, info.members);
          $task.data('taskInfo', info);
          $container.append($task);
        });
      }
    },

    buildBadges: function($wrap, data) {
      var self = this,
        html = '',
        duedate = data.duedate,
        subtasks = data.subtasks,
        attachments = data.attachments,
        comments = data.comments;

      if (duedate.length > 0) {
        html += self.badgesTpl('duedate', duedate.split(/\//, 2).join("/"));
      }

      if (subtasks.length > 0) {
        var num = 0;
        $.each(subtasks, function(n, i) {
          if (i.complete) num++;
        });

        html += self.badgesTpl('subtasks', num + '/' + subtasks.length);
      }

      if (attachments.length > 0) {
        html += self.badgesTpl('attachments', attachments.length);
      }

      if (comments.length > 0) {
        html += self.badgesTpl('comments', comments.length);
      }

      $wrap.find('.task-badges').html(html);
    },

    buildMembers: function($wrap, data) {
      var self = this,
        html = '';
      if (data.length === 0) return;
      $.each(data, function(i, n) {
        html += self.membersTpl(n.img);
      });
      $wrap.find('.task-members').html(html);
    },

    //Sortable
    initSortable: function() {
      $('.taskboard-stages').sortable({
        handle: ".taskboard-stage-header"
      });
      $('.taskboard-stage .list-group').sortable({
        connectWith: ".taskboard-stage .list-group"
      });
    },

    //Stage
    handleAddStage: function() {
      var self = this;

      $(document).on('click', '.site-floataction', function() {
        var $model = $('#addStageFrom');

        $('input', $model).val('');
        $('option:first', $('select', $model)).prop("selected", 'selected');
      });

      $(document).on('click', '#taskboard-stage-creat', function() {
        var $this = $(this),
          $model = $('#addStageFrom'),
          $name = $('[name="name"]', $model);

        $('.taskboard-stages').append(self.stageTpl($name.val()));
        self.initSortable();
      });
    },

    handleDeleteStage: function() {
      $(document).on('click', '.taskboard-stage-delete', function() {
        var $this = $(this);
        bootbox.dialog({
          message: "Do you want to delete the stage?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                $this.closest('.taskboard-stage').remove();
              }
            }
          }
        });
      });
    },

    getStage: function($task) {
      return $task.closest('.taskboard-stage');
    },

    //Stage Dropdown
    initStageDropdown: function() {
      $(document).on('click', '.taskboard-stage-actions .dropdown-toggle', function() {
        $(this).next('.dropdown-menu').removeClass('is-edit');

        //judge dropdown side
      });
    },

    handleStageRename: function() {
      $(document).on('click', '.taskboard-stage-rename', function(e) {
        var $header = $(this).closest('.taskboard-stage-header'),
          $menu = $(this).closest('.dropdown-menu'),
          $input = $('.taskboard-stage-rename-input', $menu),
          $title = $('.taskboard-stage-title', $header);

        $menu.toggleClass('is-edit');
        $input.val('').focus().val($title.html());
        e.stopPropagation();
      });

      $(document).on('click', '.taskboard-stage-rename-save', function() {
        var $header = $(this).closest('.taskboard-stage-header'),
          $input = $('.taskboard-stage-rename-input', $header),
          $title = $('.taskboard-stage-title', $header),
          value = $input.val();

        if (value.length === 0) return;

        $title.html(value);
      });
    },

    //Task
    handleAddTask: function() {
      var self = this;

      $(document).on('click', '.add-item-toggle, .add-item-add, .add-item-cancel', function() {
        var $this = $(this),
          $wrap = $this.closest('.action-wrap'),
          $input = $('[name="name"]', $wrap);

        $wrap.toggleClass('action-open');
        if ($this.hasClass('add-item-toggle')) {
          $input.val('');
        }

        if ($this.hasClass('add-item-toggle')) {
          $(document).on('click.add-item', function(e) {
            var $target = $(e.target);
            if ($target.closest('.add-item-wrap').length === 0) {
              $wrap.removeClass('action-open');
              $(document).off('click.add-item');
            }
          });
        } else {
          $(document).off('click.add-item');
        }
      });

      $(document).on('click', '.add-item-add', function() {
        var $this = $(this),
          $wrap = $this.closest('.action-wrap'),
          $input = $('[name="name"]', $wrap),
          $list = $('.taskboard-list', $this.closest('.taskboard-stage-content')),
          data = self.dataTpl();

        if ($input.val().length === 0) {
          return;
        }

        data.title = $input.val();
        self.buildTask($list, data, true);
      });
    },

    handleDeleteTask: function() {
      $(document).on('click', '.taskboard-task-delete', function() {
        var $this = $(this);
        bootbox.dialog({
          message: "Do you want to delete the task?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                $this.closest('.slidePanel').data('slidePanel').target.remove();
                $('.slidePanel-close').trigger('click');
              }
            }
          }
        });
      });
    },

    handleTaskInput: function() {
      var self = this;
      $(document).on('click', '.taskboard-list .checkbox-custom input', function(e) {
        var $this = $(this),
          $target = $this.closest('.list-group-item');

        self.dataChange($target, 'complete', $this.prop("checked"));
        e.stopPropagation();
      });
    },

    //Init SlidePanel
    handlSlidePanelPlugin: function() {
      if (typeof $.slidePanel === 'undefined') return;
      var self = this;
      var defaults = $.components.getDefaults("slidePanel");
      var options = $.extend({}, defaults, {
        template: function(options) {
          return '<div class="' + options.classes.base + ' ' + options.classes.base + '-' + options.direction + '">' +
            '<div class="' + options.classes.base + '-scrollable"><div>' +
            '<div class="' + options.classes.content + '"></div>' +
            '</div></div>' +
            '<div class="' + options.classes.base + '-handler"></div>' +
            '</div>';
        },
        afterLoad: function(object) {
          var _this = this,
            $target = $(object.target),
            info = $target.data('taskInfo');

          this.$panel.find('.' + this.options.classes.base + '-scrollable').asScrollable({
            namespace: 'scrollable',
            contentSelector: '>',
            containerSelector: '>'
          });

          this.$panel.find('#task-description').markdown();
          if (info.duedate.length > 0) {
            this.$panel.find('#taskDatepicker').data('date', info.duedate);
          }
          this.$panel.find('#taskDatepicker').datepicker({
            autoclose: false,
            todayHighlight: true,
          }).on('changeDate', function() {
            $('#taskDatepickerInput').val(
              _this.$panel.find('#taskDatepicker').datepicker('getFormattedDate')
            );
          });;


          this.$panel.data('slidePanel', object);

          $(document).off('click.slidePanelDatepicker');
          $(document).on('click.slidePanelDatepicker', 'span, td, th', function(e) {
            e.stopPropagation();
          });
        },
        afterShow: function() {
          var self = this;
          $(document).on('click.slidePanelShow', function(e) {
            if ($(e.target).closest('.slidePanel').length === 0 && $(e.target).closest('body').length === 1) {
              self.hide();
            }
          });
        },
        afterHide: function() {
          $(document).off('click.slidePanelShow');
          $(document).off('click.slidePanelDatepicker');
        },
        contentFilter: function(data, object) {
          var $target = $(object.target),
            info = $target.data('taskInfo'),
            $panel = $(data),
            $checked;

          $('.stage-name', $panel).html($('.taskboard-stage-title', self.getStage($target)).html());

          $('.task-title', $panel).html(info.title);

          switch (info.priority) {
            case 'high':
              $checked = $('#priorityHigh', $panel);
              break;
            case 'urgent':
              $checked = $('#priorityUrgent', $panel);
              break;
            default:
              $checked = $('#priorityNormal', $panel);
              break;
          }
          $checked.prop("checked", true);

          self.handleSelective($('[data-plugin="jquery-selective"]', $panel), info.members);

          if (info.description.length === 0) {
            $('.description', $panel).addClass('is-empty');
          } else {
            $('.description-content', $panel).html(info.description);
          }

          if (info.subtasks.length !== 0) {
            $.each(info.subtasks, function(n, subtask) {
              var $subtask = $(self.subtaskTpl(subtask));
              $('.subtasks-list', $panel).append($subtask);
            });
            $('.subtasks', $panel).toggleClass('is-show');
          }

          if (info.attachments.length !== 0) {
            $.each(info.attachments, function(n, attachment) {
              var $attachment = $(self.attachmentTpl(attachment));
              $('.attachments-list', $panel).append($attachment);
            });
            $('.attachments', $panel).toggleClass('is-show');
          }

          if (info.comments.length !== 0) {
            $.each(info.comments, function(n, comment) {
              var $comment = $(self.commentTpl(comment.src, comment.user, comment.time, comment.content));
              $('.comments-history', $panel).append($comment);
            });
          }

          return $panel;
        }
      });

      $(document).on('click', '[data-taskboard="slidePanel"]', function(e) {
        var $target = $(e.target).closest('.list-group-item');
        $.slidePanel.show({
          url: $(this).data('url'),
          target: $target
        }, options);

        e.stopPropagation();
      });

      $(document).on('click', '#fileuploadToggle', function() {
        $('#fileupload').trigger('click');
      });
    },

    //SlidePanel Section Handle
    handleSelective: function($target, selected) {
      var self = this;
      var getSelected = function() {
        var _this = this,
          arr = [];
        $.each(this._options.getOptions(this), function(n, option) {
          $.each(_this.options.local, function(i, user) {
            if (user.id === $(option).val()) {
              arr.push(user);
            }
          });
        });
        return arr;
      };
      var members = [{
        id: 'uid_1',
        name: 'Herman Beck',
        img: '../../../../global/portraits/1.jpg'
      }, {
        id: 'uid_2',
        name: 'Mary Adams',
        img: '../../../../global/portraits/2.jpg'
      }, {
        id: 'uid_3',
        name: 'Caleb Richards',
        img: '../../../../global/portraits/3.jpg'
      }, {
        id: 'uid_4',
        name: 'June Lane',
        img: '../../../../global/portraits/4.jpg'
      }, {
        id: 'uid_5',
        name: 'Edward Fletcher',
        img: '../../../../global/portraits/5.jpg'
      }, {
        id: 'uid_6',
        name: 'Crystal Bates',
        img: '../../../../global/portraits/6.jpg'
      }];

      $target.selective({
        namespace: 'addMember',
        local: members,
        selected: selected,
        buildFromHtml: false,
        tpl: {
          optionValue: function(data) {
            return data.id;
          },
          frame: function() {
            return '<div class="' + this.namespace + '">' +
              this.options.tpl.items.call(this) +
              '<div class="' + this.namespace + '-trigger">' +
              this.options.tpl.triggerButton.call(this) +
              '<div class="' + this.namespace + '-trigger-dropdown">' +
              this.options.tpl.list.call(this) +
              '</div>' +
              '</div>' +
              '</div>'
          },
          triggerButton: function() {
            return '<div class="' + this.namespace + '-trigger-button"><i class="md-plus"></i></div>';
          },
          listItem: function(data) {
            return '<li class="' + this.namespace + '-list-item"><img class="avatar" src="' + data.img + '">' + data.name + '</li>';
          },
          item: function(data) {
            return '<li class="' + this.namespace + '-item"><img class="avatar" src="' + data.img + '">' +
              this.options.tpl.itemRemove.call(this) +
              '</li>';
          },
          itemRemove: function() {
            return '<span class="' + this.namespace + '-remove"><i class="md-minus-circle"></i></span>';
          },
          option: function(data) {
            return '<option value="' + this.options.tpl.optionValue.call(this, data) + '">' + data.name + '</option>';
          }
        },
        onAfterItemAdd: function() {
          var $target = this.$el.closest('.slidePanel').data('slidePanel').target,
            arr = getSelected.call(this);
          self.dataChange($target, 'members', arr);
        },
        onAfterItemRemove: function() {
          var $target = this.$el.closest('.slidePanel').data('slidePanel').target,
            arr = getSelected.call(this);
          self.dataChange($target, 'members', arr);
        }
      });
    },

    handlePriority: function() {
      var self = this;
      $(document).on('click', '[name="priorities"]', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target;

        self.dataChange($target, 'priority', $this.data('priority'));
      });
    },

    handleEditor: function() {
      var self = this;
      $(document).on('click', '.slidePanel .task-title, .taskboard-task-edit, .description-toggle', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          data = $target.data('taskInfo');

        $('#task-title').val(data.title);
        $('#task-description').val(data.description);
        $this.closest('.slidePanel').find('.task-main').addClass('is-edit');
      });

      $(document).on('click', '.task-main-editor-save', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          data = $target.data('taskInfo');

        self.dataChange($target, 'title', $('#task-title').val());
        self.dataChange($target, 'description', $('#task-description').val());

        $this.closest('.slidePanel').find('.task-main').removeClass('is-edit');
        if ($('#task-description').val().length === 0) {
          $('.description').addClass('is-empty');
        } else {
          $('.description').removeClass('is-empty');
        }
      });

      $(document).on('click', '.task-main-editor-cancel', function() {
        $(this).closest('.slidePanel').find('.task-main').removeClass('is-edit');
      });
    },

    handleSubtasks: function() {
      var self = this;
      $(document).on('click', '.subtask-toggle', function() {
        var length = $('.subtask').length,
          $input = $('.subtasks-add .subtask-title'),
          $subtasks = $('.subtasks');

        $input.val('');
        if (length === 0) {
          $subtasks.addClass('is-show');
        }
        $subtasks.addClass('is-edit');

        $input.focus();

        $(document).on('click.subtask-add', function(e) {
          var $target = $(e.target);
          if ($target.closest($('.subtasks-add')).length === 0) {
            $subtasks.removeClass('is-edit');
            $(document).off('click.subtask-add');
          }
        });
      });

      $(document).on('click', '.subtask-add-save', function() {
        var length = $('.subtask').length,
          $subtasks = $('.subtasks'),
          $input = $('.subtasks-add .subtask-title'),
          value = $input.val(),
          $target = $(this).closest('.slidePanel').data('slidePanel').target;

        if (value.length === 0) {
          if (length === 0) {
            $subtasks.removeClass('is-show');
          }
        } else {
          var data = {
              'title': value,
              'complete': false
            },
            $subtask = $(self.subtaskTpl(data));

          $('.subtasks-list').append($subtask);
          self.dataChange($target, 'subtasks', data, length);
        }
        $input.val('').focus();
      });

      $(document).on('click', '.subtask-add-cancel', function() {
        $('.subtasks').removeClass('is-edit');
        $(document).off('click.subtask-add');
      });

      $(document).on('click', '.subtask input', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          $subtask = $this.closest('.subtask'),
          index = $subtask.index();

        self.dataChange($target, 'subtasks', $this.prop("checked"), index, 'complete');
      });

      $(document).on('click', '.subtask .title', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          data = $target.data('taskInfo'),
          $subtask = $this.closest('.subtask'),
          index = $subtask.index(),
          $input = $('.subtask-title', $subtask);

        $subtask.addClass('is-edit');
        $input.val('').focus().val(data.subtasks[index].title);

        $(document).on('click.subtask', function(e) {
          var $target = $(e.target);
          if ($target.closest($subtask).length === 0) {
            $subtask.removeClass('is-edit');
            $(document).off('click.subtask');
          }
        });
      });

      $(document).on('click', '.subtask-editor-save', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          data = $target.data('taskInfo'),
          $subtask = $this.closest('.subtask'),
          index = $subtask.index();

        self.dataChange($target, 'subtasks', $('.subtask-title', $subtask).val(), index, 'title');
        $subtask.removeClass('is-edit');
        $(document).off('click.subtask');
      });

      $(document).on('click', '.subtask-editor-delete', function(e) {
        var $this = $(this);

        bootbox.dialog({
          message: "Do you want to delete the subtask?",
          buttons: {
            success: {
              label: "Delete",
              className: "btn-danger",
              callback: function() {
                var $target = $this.closest('.slidePanel').data('slidePanel').target,
                  data = $target.data('taskInfo'),
                  $subtask = $this.closest('.subtask'),
                  index = $subtask.index();
                self.dataDelete($target, 'subtasks', index);
                $subtask.remove();
                $(document).off('click.subtask');
                if ($('.subtask').length === 0) {
                  $('.subtasks').removeClass('is-show');
                }
              }
            }
          }
        });

      });
    },

    handleDatepicker: function() {
      var self = this;
      $(document).on('click', '.due-date-save', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          value = $('#taskDatepickerInput').val();
        if (value.length > 0) {
          self.dataChange($target, 'duedate', value);
        }
      });
      $(document).on('click', '.due-date-delete', function() {
        var $this = $(this),
          $target = $this.closest('.slidePanel').data('slidePanel').target,
          data = $target.data('taskInfo');
        if (data.duedate.length === 0) return;
        self.dataDelete($target, 'duedate');
        $('#taskDatepicker').datepicker('clearDates');
      });
    },

    //Data
    dataDelete: function($target, name, index) {
      if (index) {
        $target.data('taskInfo')[name].splice(index, 1);
        this.dataDeleteResponse($target, name, index);
      } else {
        $target.data('taskInfo')[name] = '';
        this.dataChangeResponse($target, name);
      }
    },

    dataDeleteResponse: function($target, name, index) {
      switch (name) {
        case 'duedate':
          this.buildBadges($target, $target.data('taskInfo'));
          break;
        case 'subtasks':
          this.buildBadges($target, $target.data('taskInfo'));
          break;
      }
    },

    dataChange: function($target, name, content, index, subname) {
      if (content.length === 0) return;
      if (index !== undefined) {
        if (subname) {
          $target.data('taskInfo')[name][index][subname] = content;
        } else {
          $target.data('taskInfo')[name][index] = content;
        }
      } else {
        $target.data('taskInfo')[name] = content;
      }
      this.dataChangeResponse($target, name, content, index, subname);
    },

    dataChangeResponse: function($target, name, content, index, subname) {
      switch (name) {
        case 'title':
          $('.task-title', $target).html(content);
          $('.slidePanel .task-title').html(content);
          break;
        case 'description':
          $('.slidePanel .description-content').html(content);
          break;
        case 'priority':
          $target.removeClass('priority-normal priority-high priority-urgent').addClass('priority-' + content);
          break;
        case 'duedate':
          this.buildBadges($target, $target.data('taskInfo'));
          break;
        case 'members':
          this.buildMembers($target, $target.data('taskInfo').members);
          break;
        case 'subtasks':
          if (subname === 'title') {
            $('.title', $('.subtasks-list .subtask')[index]).html(content);
          } else {
            this.buildBadges($target, $target.data('taskInfo'));
          }
          break;
        case 'attachments':
          break;
        case 'comments':
          break;
      }
    },

    run: function(next) {
      var self = this;

      this.init();

      this.handleAddStage();
      this.handleDeleteStage();

      this.handleAddTask();
      this.handleDeleteTask();

      this.handleTaskInput();

      this.initStageDropdown();
      this.handleStageRename();

      this.handleDatepicker();
      this.handlSlidePanelPlugin();

      this.handleEditor();
      this.handleSubtasks();
      this.handlePriority();


      next();
    }
  });

  $(document).ready(function() {
    AppTaskboard.run();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  window.AppCalendar = App.extend({
    handleChart: function() {

      /* create line chart */

      var scoreChart = function(data) {

        var scoreChart = new Chartist.Line(data, {
          labels: ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"],
          series: [{
            name: "series-1",
            data: [0.8, 1.5, 0.8, 2.7, 2.4, 3.9, 1.1]
          }, {
            name: "series-2",
            data: [2.2, 3, 2.7, 3.6, 1.5, 1, 2.9]
          }]
        }, {
          lineSmooth: Chartist.Interpolation.simple({
            divisor: 100
          }),
          fullWidth: true,
          chartPadding: {
            right: 25
          },
          series: {
            "series-1": {
              showArea: false
            },
            "series-2": {
              showArea: false
            }
          },
          axisX: {
            showGrid: false
          },
          axisY: {
            scaleMinSpace: 40,
          },
          plugins: [
            Chartist.plugins.tooltip()
          ],
          low: 0,
          height: 250
        });
        scoreChart.on('draw', function(data) {
          if (data.type === 'point') {
            var parent = new Chartist.Svg(data.element._node.parentNode);
            parent.elem('line', {
              x1: data.x,
              y1: data.y,
              x2: data.x + 0.01,
              y2: data.y,
              "class": 'ct-point-content'
            });
          }
        });

      }

      var WeekLabelList = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
      var WeekSeries1List = {
        name: "series-1",
        data: [0.8, 1.5, 0.8, 2.7, 2.4, 3.9, 1.1]
      };
      var WeekSeries2List = {
        name: "series-2",
        data: [2.2, 3, 2.7, 3.6, 1.5, 1, 2.9]
      };

      /* create bar chart */
      var barChart = function(data) {
        var barChart = new Chartist.Bar(data, {
          labels: ['Damon', 'Jimmy', 'Jhon', 'Alex', 'Lucy', 'Peter', 'Chris'],
          series: [
            [3.3, 3.5, 2.5, 2, 3.7, 2.7, 1.9],
            [2, 4, 3.5, 2.7, 3.3, 3.5, 2.5]
          ]
        }, {
          axisX: {
            showGrid: false
          },
          axisY: {
            showGrid: false,
            scaleMinSpace: 30
          },
          height: 210,
          seriesBarDistance: 24
        });

        barChart.on('draw', function(data) {
          if (data.type === 'bar') {
            var parent = new Chartist.Svg(data.element._node.parentNode);
            parent.elem('line', {
              x1: data.x1,
              x2: data.x2,
              y1: data.y2,
              y2: 0,
              "class": 'ct-bar-fill'
            });

            data.element.attr({
              style: 'stroke-width: 20px'
            });
          }
        });
      }

      /* run chart */
      $(document).on('slidePanel::afterLoad', function() {
        scoreChart('.trends-chart');
        barChart('.member-chart');
      });
    },
    handleSelective: function() {
      var self = this;
      var member = [{
        id: 'uid_1',
        name: 'Herman Beck',
        avatar: '../../../../global/portraits/1.jpg'
      }, {
        id: 'uid_2',
        name: 'Mary Adams',
        avatar: '../../../../global/portraits/2.jpg'
      }, {
        id: 'uid_3',
        name: 'Caleb Richards',
        avatar: '../../../../global/portraits/3.jpg'
      }, {
        id: 'uid_4',
        name: 'June Lane',
        avatar: '../../../../global/portraits/4.jpg'
      }, {
        id: 'uid_5',
        name: 'June Lane',
        avatar: '../../../../global/portraits/5.jpg'
      }, {
        id: 'uid_6',
        name: 'June Lane',
        avatar: '../../../../global/portraits/6.jpg'
      }, {
        id: 'uid_7',
        name: 'June Lane',
        avatar: '../../../../global/portraits/7.jpg'
      }];

      function getNum(num) {
        return Math.ceil(Math.random() * (num + 1));
      }

      function getMember() {
        return member[getNum(member.length - 1) - 1];
      }

      function isSame(items) {
        var _items = items;
        var _member = getMember();

        if (_items.indexOf(_member) === -1) {
          return _member;
        } else {
          return isSame(_items);
        }
      }

      function pushMember(num) {
        var items = [];
        for (var i = 0; i < num; i++) {
          items.push(isSame(items));
        }
        self.items = items;
      };

      function setItems(membersNum) {
        var num = getNum(membersNum - 1);
        pushMember(num);
      }

      $('[data-plugin="jquery-selective"]').each(function() {

        setItems(member.length);

        var items = self.items;

        $(this).selective({
          namespace: 'addMember',
          local: member,
          selected: items,
          buildFromHtml: false,
          tpl: {
            optionValue: function(data) {
              return data.id;
            },
            frame: function() {
              return '<div class="' + this.namespace + '">' +
                this.options.tpl.items.call(this) +
                '<div class="' + this.namespace + '-trigger">' +
                this.options.tpl.triggerButton.call(this) +
                '<div class="' + this.namespace + '-trigger-dropdown">' +
                this.options.tpl.list.call(this) +
                '</div>' +
                '</div>' +
                '</div>'

              i++;
            },
            triggerButton: function() {
              return '<div class="' + this.namespace + '-trigger-button"><i class="md-plus"></i></div>';
            },
            listItem: function(data) {
              return '<li class="' + this.namespace + '-list-item"><img class="avatar" src="' + data.avatar + '">' + data.name + '</li>';
            },
            item: function(data) {
              return '<li class="' + this.namespace + '-item"><img class="avatar" src="' + data.avatar + '" title="' + data.name + '">' +
                this.options.tpl.itemRemove.call(this) +
                '</li>';
            },
            itemRemove: function() {
              return '<span class="' + this.namespace + '-remove"><i class="md-minus-circle"></i></span>';
            },
            option: function(data) {
              return '<option value="' + this.options.tpl.optionValue.call(this, data) + '">' + data.name + '</option>';
            }
          }
        });
      });

    },
    run: function() {
      this.items = [];

      this.handleChart();
      this.handleSelective();
    }
  });

  $(document).ready(function() {
    AppCalendar.run();
  });
})(document, window, jQuery);

$(document).ready(function($) {
  Site.run();

  // Widget Linearea One
  // ---------------------
  (function() {
    //chart-linearea-one
    new Chartist.Line('#widgetLineareaOne .ct-chart', {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8'],
      series: [
        [0, 1, 3, 2, 3.5, 1.2, 1.5, 0]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });
  })();

  // Widget Linearea Two
  // ---------------------
  (function() {
    //chart-linearea-two
    new Chartist.Line('#widgetLineareaTwo .ct-chart', {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9'],
      series: [
        [0, 0.5, 2.2, 2, 2.8, 2.3, 3.3, 2.5, 0]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });
  })();

  // Widget Linearea Three
  // ---------------------
  (function() {
    //chart-linearea-three
    new Chartist.Line('#widgetLineareaThree .ct-chart', {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8'],
      series: [
        [0, 2, 1.5, 3.5, 2.2, 3, 0.8, 0]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });
  })();

  // Widget Linearea Four
  // ---------------------
  (function() {
    //chart-linearea-four
    new Chartist.Line('#widgetLineareaFour .ct-chart', {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9'],
      series: [
        [0, 1.2, 2.4, 2.5, 3.5, 2, 2.5, 1.2, 0]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });
  })();

  // Widget VectorMap
  // ----------------
  (function() {
    var defaults = $.components.getDefaults('vectorMap');
    var options = $.extend({}, defaults, {
      markers: [{
        latLng: [39.9, 116.3],
        name: '1,512 Visits'
      }, {
        latLng: [40.43, -75],
        name: '940 Visits'
      }, {
        latLng: [-33.55, 151],
        name: '340 Visits'
      }],
    }, true);

    $('#widgetJvmap').vectorMap(options);
  })();

  // Widget Current Chart
  // --------------------
  (function() {
    //chart-bar-withfooter
    new Chartist.Bar('#widgetCurrentChart .ct-chart', {
      labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      series: [
        [160, 390, 280, 440, 410, 360, 200],
        [600 - 160, 600 - 390, 600 - 280, 600 - 440, 600 - 410, 600 - 360, 600 - 200]
      ]
    }, {
      stackBars: true,
      fullWidth: true,
      seriesBarDistance: 0,
      axisX: {
        showLabel: true,
        showGrid: false,
        offset: 30
      },
      axisY: {
        showLabel: true,
        showGrid: false,
        offset: 30,
        labelOffset: {
          x: 0,
          y: 15
        }
      }
    });
  })();

  Waves.attach('.page-content .btn-floating', ['waves-light']);
});

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  $(document).ready(function($) {
    Site.run();
  });

  // Example Reset Current
  // ---------------------
  (function() {
    // Reset Current
    $('#exampleTimeButton').on('click', function() {
      $('#inputTextCurrent').timepicker('setTime', new Date());
    });
  })();

  // Example inline datepicker
  // ---------------------
  (function() {
    // Reset Current
    $('#inlineDatepicker').datepicker();
    $("#inlineDatepicker").on("changeDate", function(event) {
      $("#inputHiddenInline").val(
        $("#inlineDatepicker").datepicker('getFormattedDate')
      );
    });
  })();

  // Example Tokenfield With Typeahead
  // ---------------------------------
  (function() {
    var engine = new Bloodhound({
      local: [{
        value: 'red'
      }, {
        value: 'blue'
      }, {
        value: 'green'
      }, {
        value: 'yellow'
      }, {
        value: 'violet'
      }, {
        value: 'brown'
      }, {
        value: 'purple'
      }, {
        value: 'black'
      }, {
        value: 'white'
      }],
      datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
      queryTokenizer: Bloodhound.tokenizers.whitespace
    });

    // engine.initialize();

    $('#inputTokenfieldTypeahead').tokenfield({
      typeahead: [null, {
        name: 'engine',
        displayKey: 'value',
        source: engine.ttAdapter()
      }]
    });
  })();


  // Example Tokenfield Events
  // -------------------------
  (function() {
    $('#inputTokenfieldEvents')
      .on('tokenfield:createtoken', function(e) {
        var data = e.attrs.value.split('|');
        e.attrs.value = data[1] || data[0];
        e.attrs.label = data[1] ? data[0] + ' (' + data[1] + ')' : data[0];
      })
      .on('tokenfield:createdtoken', function(e) {
        // Über-simplistic e-mail validation
        var re = /\S+@\S+\.\S+/;
        var valid = re.test(e.attrs.value);
        if (!valid) {
          $(e.relatedTarget).addClass('invalid');
        }
      })
      .on('tokenfield:edittoken', function(e) {
        if (e.attrs.label !== e.attrs.value) {
          var label = e.attrs.label.split(' (');
          e.attrs.value = label[0] + '|' + e.attrs.value;
        }
      })
      .on('tokenfield:removedtoken', function(e) {
        if (e.attrs.length > 1) {
          var values = $.map(e.attrs, function(attrs) {
            return attrs.value;
          });
          alert(e.attrs.length + ' tokens removed! Token values were: ' + values.join(', '));
        } else {
          alert('Token removed! Token value was: ' + e.attrs.value);
        }
      })
      .tokenfield();
  })();


  // Example Tags Input Objects as tags
  // ----------------------------------
  (function() {
    var cities = new Bloodhound({
      datumTokenizer: Bloodhound.tokenizers.obj.whitespace('text'),
      queryTokenizer: Bloodhound.tokenizers.whitespace,
      prefetch: '../../assets/data/cities.json'
    });
    cities.initialize();

    var options = $.extend(true, {}, $.components.getDefaults("tagsinput"), {
      itemValue: 'value',
      itemText: 'text',
      typeaheadjs: [{
        hint: true,
        highlight: true,
        minLength: 1
      }, {
        name: 'cities',
        displayKey: 'text',
        source: cities.ttAdapter()
      }]
    });

    var $input = $('#inputTagsObject');
    $input.tagsinput(options);

    $input.tagsinput('add', {
      "value": 1,
      "text": "Amsterdam",
      "continent": "Europe"
    });
    $input.tagsinput('add', {
      "value": 4,
      "text": "Washington",
      "continent": "America"
    });
    $input.tagsinput('add', {
      "value": 7,
      "text": "Sydney",
      "continent": "Australia"
    });
    $input.tagsinput('add', {
      "value": 10,
      "text": "Beijing",
      "continent": "Asia"
    });
    $input.tagsinput('add', {
      "value": 13,
      "text": "Cairo",
      "continent": "Africa"
    });
  })();

  // Example Tags Input Categorizing
  // -------------------------------
  (function() {
    var cities = new Bloodhound({
      datumTokenizer: Bloodhound.tokenizers.obj.whitespace('text'),
      queryTokenizer: Bloodhound.tokenizers.whitespace,
      prefetch: '../../assets/data/cities.json'
    });
    cities.initialize();

    var options = $.extend(true, {}, $.components.getDefaults("tagsinput"), {
      tagClass: function(item) {
        switch (item.continent) {
          case 'Europe':
            return 'label label-primary';
          case 'America':
            return 'label label-danger';
          case 'Australia':
            return 'label label-success';
          case 'Africa':
            return 'label label-default';
          case 'Asia':
            return 'label label-warning';
        }
      },
      itemValue: 'value',
      itemText: 'text',
      typeaheadjs: [{
        hint: true,
        highlight: true,
        minLength: 1
      }, {
        name: 'cities',
        displayKey: 'text',
        source: cities.ttAdapter()
      }]
    });
    var $input = $('#inputTagsCategorizing');

    $input.tagsinput(options);

    $input.tagsinput('add', {
      "value": 1,
      "text": "Amsterdam",
      "continent": "Europe"
    });
    $input.tagsinput('add', {
      "value": 4,
      "text": "Washington",
      "continent": "America"
    });
    $input.tagsinput('add', {
      "value": 7,
      "text": "Sydney",
      "continent": "Australia"
    });
    $input.tagsinput('add', {
      "value": 10,
      "text": "Beijing",
      "continent": "Asia"
    });
    $input.tagsinput('add', {
      "value": 13,
      "text": "Cairo",
      "continent": "Africa"
    });

  })();


  // Example AsSpinner
  // -----------------
  (function() {
    // Custom Format
    var options = $.extend({}, $.components.getDefaults("asSpinner"), {
      format: function(value) {
        return value + '%';
      }
    });

    $('#inputSpinnerCustomFormat').asSpinner(options);
  })();


  // Example Multi-Select
  // --------------------
  (function() {
    // for multi-select public methods example
    $('.multi-select-methods').multiSelect();
    $('#buttonSelectAll').click(function() {
      $('.multi-select-methods').multiSelect('select_all');
      return false;
    });
    $('#buttonDeselectAll').click(function() {
      $('.multi-select-methods').multiSelect('deselect_all');
      return false;
    });
    $('#buttonSelectSome').click(function() {
      $('.multi-select-methods').multiSelect('select', ['Idaho', 'Montana', 'Arkansas']);
      return false;
    });
    $('#buttonDeselectSome').click(function() {
      $('.multi-select-methods').multiSelect('select', ['Idaho', 'Montana', 'Arkansas']);
      return false;
    });
    $('#buttonRefresh').on('click', function() {
      $('.multi-select-methods').multiSelect('refresh');
      return false;
    });
    $('#buttonAdd').on('click', function() {
      $('.multi-select-methods').multiSelect('addOption', {
        value: 42,
        text: 'test 42',
        index: 0
      });
      return false;
    });
  })();


  // Example Typeahead
  // -----------------
  (function() {
    var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
      'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
      'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
      'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
      'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
      'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
      'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
      'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
      'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
    ];

    // basic & Styled
    // --------------
    (function() {
      var substringMatcher = function(strs) {
        return function findMatches(q, cb) {
          var matches, substrRegex;

          // an array that will be populated with substring matches
          matches = [];

          // regex used to determine if a string contains the substring `q`
          substrRegex = new RegExp(q, 'i');

          // iterate through the pool of strings and for any string that
          // contains the substring `q`, add it to the `matches` array
          $.each(strs, function(i, str) {
            if (substrRegex.test(str)) {
              matches.push(str);
            }
          });

          cb(matches);
        };
      };

      $('#exampleTypeaheadBasic, #exampleTypeaheadStyle').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
      }, {
        name: 'states',
        source: substringMatcher(states)
      });
    })();

    // bloodhound
    // ----------
    (function() {
      var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
        'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
        'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
        'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
        'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
        'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
        'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
        'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
        'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
      ];
      // constructs the suggestion engine
      var state = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        // `states` is an array of state names defined in "The Basics"
        local: states
      });

      $('#exampleTypeaheadBloodhound').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
      }, {
        name: 'states',
        source: state
      });
    })();

    // Prefetch typeahead
    // ----------------
    (function() {
      var countries = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        // url points to a json file that contains an array of country names, see
        // https://github.com/twitter/typeahead.js/blob/gh-pages/data/countries.json
        prefetch: '../../assets/data/countries.json'
      });

      // passing in `null` for the `options` arguments will result in the default
      // options being used
      $('#exampleTypeaheadPrefetch').typeahead(null, {
        name: 'countries',
        source: countries
      });
    })();


  })();
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;


  $(document).ready(function($) {
    Site.run();
    //enable / disable
    $('#editableEnable').click(function() {
      $('#editableUser .editable').editable('toggleDisabled');
    });


    var init_x_editable = function() {

      $.fn.editableform.buttons =
        '<button type="submit" class="btn btn-primary btn-sm editable-submit">' +
        '<i class="icon md-check" aria-hidden="true"></i>' +
        '</button>' +
        '<button type="button" class="btn btn-default btn-sm editable-cancel">' +
        '<i class="icon md-close" aria-hidden="true"></i>' +
        '</button>';

      $.fn.editabletypes.datefield.defaults.inputclass = "form-control input-sm";

      //defaults
      $.fn.editable.defaults.url = '/post';

      //editables
      $('#editableSuperuser').editable({
        url: '/post',
        type: 'text',
        pk: 1,
        name: 'username',
        title: 'Enter username'
      });

      $('#editableFirstname').editable({
        validate: function(value) {
          if ($.trim(value) === '') return 'This field is required';
        }
      });

      $('#editableSex').editable({
        prepend: "not selected",
        source: [{
          value: 1,
          text: 'Male'
        }, {
          value: 2,
          text: 'Female'
        }],
        display: function(value, sourceData) {
          var colors = {
              "": "gray",
              1: "green",
              2: "blue"
            },
            elem = $.grep(sourceData, function(o) {
              return o.value === value;
            });

          if (elem.length) {
            $(this).text(elem[0].text).css("color", colors[value]);
          } else {
            $(this).empty();
          }
        }
      });


      $('#editableVacation').editable({
        datepicker: {
          todayBtn: 'linked'
        }
      });

      $('#editableDob').editable();

      $('#editableEvent').editable({
        placement: 'right',
        combodate: {
          firstItem: 'name'
        }
      });

      $('#editableMeetingStart').editable({
        format: 'yyyy-mm-dd hh:ii',
        viewformat: 'dd/mm/yyyy hh:ii',
        validate: function(v) {
          if (v && v.getDate() === 10) return 'Day cant be 10!';
        },
        datetimepicker: {
          todayBtn: 'linked',
          weekStart: 1
        }
      });

      $('#editableComments').editable({
        showbuttons: 'bottom'
      });

      $('#editableNote').editable();
      $('#editablePencil').click(function(e) {
        e.stopPropagation();
        e.preventDefault();
        $('#editableNote').editable('toggle');
      });

      $('#editableState').editable({
        source: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"]
      });

      var editableStates = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"],
        states = new Bloodhound({
          datumTokenizer: Bloodhound.tokenizers.whitespace,
          queryTokenizer: Bloodhound.tokenizers.whitespace,
          local: editableStates
        });

      $('#editableState2').editable({
        value: 'California',
        typeahead: {
          name: 'states',
          source: states
        }
      });

      $('#editableFruits').editable({
        pk: 1,
        limit: 3,
        source: [{
          value: 1,
          text: 'banana'
        }, {
          value: 2,
          text: 'peach'
        }, {
          value: 3,
          text: 'apple'
        }, {
          value: 4,
          text: 'watermelon'
        }, {
          value: 5,
          text: 'orange'
        }]
      });


      $('#editableAddress').editable({
        url: '/post',
        value: {
          city: "Moscow",
          street: "Lenina",
          building: "12"
        },
        validate: function(value) {
          if (value.city === '') return 'city is required!';
        },
        display: function(value) {
          if (!value) {
            $(this).empty();
            return;
          }
          var html = '<b>' + $('<div>').text(value.city).html() + '</b>, ' + $('<div>').text(value.street).html() + ' st., bld. ' + $('<div>').text(value.building).html();
          $(this).html(html);
        }
      });

      // $("#editableUser").find(".form-control").addClass(".input-sm");
    };

    var destory_x_editable = function() {
      $('#editableSuperuser').editable('destroy');
      $('#editableFirstname').editable('destroy');
      $('#editableSex').editable('destroy');
      // $('#editableStatus').editable('destroy');
      $('#editableVacation').editable('destroy');
      $('#editableDob').editable('destroy');
      $('#editableEvent').editable('destroy');
      $('#editableMeetingStart').editable('destroy');
      $('#editableComments').editable('destroy');
      $('#editableNote').editable('destroy');
      $('#editablePencil').editable('destroy');
      $('#editableState').editable('destroy');
      $('#editableState2').editable('destroy');
      $('#editableFruits').editable('destroy');
      $('#editableAddress').editable('destroy');
    };

    $.fn.editable.defaults.mode = 'inline';
    init_x_editable();

    // $('#editableControls').on("click", "label", function() {
    //   xMode = $(this).find("input").val();
    //   $.fn.editable.defaults.mode = xMode;
    //   destory_x_editable();
    //   init_x_editable();
    // });
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  window.edit = function() {
    $('.click2edit').summernote();
  };
  window.save = function() {
    $('.click2edit').destroy();
  };
})(document, window, jQuery);

jQuery(document).ready(function() {
  Form.init();
});

(function(document, window, $) {

  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Cropper Simple
  // ----------------------
  (function() {
    $("#simpleCropper img").cropper({
      preview: "#simpleCropperPreview >.img-preview",
      responsive: true
    });
  })();


  // Example Cropper Full
  // --------------------
  (function() {
    var $exampleFullCropper = $("#exampleFullCropper img"),
      $inputDataX = $("#inputDataX"),
      $inputDataY = $("#inputDataY"),
      $inputDataHeight = $("#inputDataHeight"),
      $inputDataWidth = $("#inputDataWidth");

    var options = {
      aspectRatio: 16 / 9,
      preview: "#exampleFullCropperPreview > .img-preview",
      responsive: true,
      crop: function() {
        var data = $(this).data('cropper').getCropBoxData();
        $inputDataX.val(Math.round(data.left));
        $inputDataY.val(Math.round(data.top));
        $inputDataHeight.val(Math.round(data.height));
        $inputDataWidth.val(Math.round(data.width));
      }
    };
    // set up cropper
    $exampleFullCropper.cropper(options);

    // set up method buttons
    $(document).on("click", "[data-cropper-method]", function() {
      var data = $(this).data(),
        method = $(this).data('cropper-method'),
        result;
      if (method) {
        result = $exampleFullCropper.cropper(method, data.option);
      }

      if (method === 'getCroppedCanvas') {
        $('#getDataURLModal').modal().find('.modal-body').html(result);
      }

    });

    // deal wtih uploading
    var $inputImage = $("#inputImage");

    if (window.FileReader) {
      $inputImage.change(function() {
        var fileReader = new FileReader(),
          files = this.files,
          file;

        if (!files.length) {
          return;
        }

        file = files[0];

        if (/^image\/\w+$/.test(file.type)) {
          fileReader.readAsDataURL(file);
          fileReader.onload = function() {
            $exampleFullCropper.cropper("reset", true).cropper("replace", this.result);
            $inputImage.val("");
          };
        } else {
          showMessage("Please choose an image file.");
        }
      });
    } else {
      $inputImage.addClass("hide");
    }

    // set data
    $("#setCropperData").click(function() {
      var data = {
        left: parseInt($inputDataX.val()),
        top: parseInt($inputDataY.val()),
        width: parseInt($inputDataWidth.val()),
        height: parseInt($inputDataHeight.val())
      };
      $exampleFullCropper.cropper("setCropBoxData", data);
    });
  })();
})(document, window, jQuery);

(function(document, window, $) {
  $(document).ready(function($) {
    Site.run();
  });

  // Example File Upload
  // -------------------
  $('#exampleUploadForm').fileupload({
    url: '../../server/fileupload/',
    dropzone: $('#exampleUploadForm'),
    filesContainer: $('.file-list'),
    uploadTemplateId: false,
    downloadTemplateId: false,
    uploadTemplate: tmpl(
      '{% for (var i=0, file; file=o.files[i]; i++) { %}' +
      '<div class="file template-upload fade col-lg-2 col-md-4 col-sm-6 {%=file.type.search("image") !== -1? "image" : "other-file"%}">' +
      '<div class="file-item">' +
      '<div class="preview vertical-align">' +
      '<div class="file-action-wrap">' +
      '<div class="file-action">' +
      '{% if (!i && !o.options.autoUpload) { %}' +
      '<i class="icon md-upload start" data-toggle="tooltip" data-original-title="Upload file" aria-hidden="true"></i>' +
      '{% } %}' +
      '{% if (!i) { %}' +
      '<i class="icon md-close cancel" data-toggle="tooltip" data-original-title="Stop upload file" aria-hidden="true"></i>' +
      '{% } %}' +
      '</div>' +
      '</div>' +
      '</div>' +
      '<div class="info-wrap">' +
      '<div class="title">{%=file.name%}</div>' +
      '</div>' +
      '<div class="progress progress-striped active" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0" role="progressbar">' +
      '<div class="progress-bar progress-bar-success" style="width:0%;"></div>' +
      '</div>' +
      '</div>' +
      '</div>' +
      '{% } %}'
    ),
    downloadTemplate: tmpl(
      '{% for (var i=0, file; file=o.files[i]; i++) { %}' +
      '<div class="file template-download fade col-lg-2 col-md-4 col-sm-6 {%=file.type.search("image") !== -1? "image" : "other-file"%}">' +
      '<div class="file-item">' +
      '<div class="preview vertical-align">' +
      '<div class="file-action-wrap">' +
      '<div class="file-action">' +
      '<i class="icon md-delete delete" data-toggle="tooltip" data-original-title="Delete files" aria-hidden="true"></i>' +
      '</div>' +
      '</div>' +
      '<img src="{%=file.url%}"/>' +
      '</div>' +
      '<div class="info-wrap">' +
      '<div class="title">{%=file.name%}</div>' +
      '</div>' +
      '</div>' +
      '</div>' +
      '{% } %}'
    ),
    forceResize: true,
    previewCanvas: false,
    previewMaxWidth: false,
    previewMaxHeight: false,
    previewThumbnail: false
  }).on('fileuploadprocessalways', function(e, data) {
    var length = data.files.length;

    for (var i = 0; i < length; i++) {
      if (!data.files[i].type.match(/^image\/(gif|jpeg|png|svg\+xml)$/)) {
        data.files[i].filetype = 'other-file';
      } else {
        data.files[i].filetype = 'image';
      }
    }
  }).on('fileuploadadded', function(e) {
    var $this = $(e.target);

    if ($this.find('.file').length > 0) {
      $this.addClass('has-file');
    } else {
      $this.removeClass('has-file');
    }
  }).on('fileuploadfinished', function(e) {
    var $this = $(e.target);

    if ($this.find('.file').length > 0) {
      $this.addClass('has-file');
    } else {
      $this.removeClass('has-file');
    }
  }).on('fileuploaddestroyed', function(e) {
    var $this = $(e.target);

    if ($this.find('.file').length > 0) {
      $this.addClass('has-file');
    } else {
      $this.removeClass('has-file');
    }
  }).on('click', function(e) {
    if ($(e.target).parents('.file').length === 0) $('#inputUpload').trigger('click');
  });

  $(document).bind('dragover', function(e) {
    var dropZone = $('#exampleUploadForm'),
      timeout = window.dropZoneTimeout;
    if (!timeout) {
      dropZone.addClass('in');
    } else {
      clearTimeout(timeout);
    }
    var found = false,
      node = e.target;
    do {
      if (node === dropZone[0]) {
        found = true;
        break;
      }
      node = node.parentNode;
    } while (node !== null);
    if (found) {
      dropZone.addClass('hover');
    } else {
      dropZone.removeClass('hover');
    }
    window.dropZoneTimeout = setTimeout(function() {
      window.dropZoneTimeout = null;
      dropZone.removeClass('in hover');
    }, 100);
  });

  $('#inputUpload').on('click', function(e) {
    e.stopPropagation();
  });

  $('#uploadlink').on('click', function(e) {
    e.stopPropagation();
  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  $(document).ready(function($) {
    Site.run();
  });

  // Example Validataion Full
  // ------------------------
  (function() {
    $('#exampleFullForm').formValidation({
      framework: "bootstrap",
      button: {
        selector: '#validateButton1',
        disabled: 'disabled'
      },
      icon: null,
      fields: {
        username: {
          validators: {
            notEmpty: {
              message: 'The username is required'
            },
            stringLength: {
              min: 6,
              max: 30
            },
            regexp: {
              regexp: /^[a-zA-Z0-9]+$/
            }
          }
        },
        email: {
          validators: {
            notEmpty: {
              message: 'The username is required'
            },
            emailAddress: {
              message: 'The email address is not valid'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: 'The password is required'
            },
            stringLength: {
              min: 8
            }
          }
        },
        birthday: {
          validators: {
            notEmpty: {
              message: 'The birthday is required'
            },
            date: {
              format: 'YYYY/MM/DD'
            }
          }
        },
        github: {
          validators: {
            url: {
              message: 'The url is not valid'
            }
          }
        },
        skills: {
          validators: {
            notEmpty: {
              message: 'The skills is required'
            },
            stringLength: {
              max: 300
            }
          }
        },
        porto_is: {
          validators: {
            notEmpty: {
              message: 'Please specify at least one'
            }
          }
        },
        'for[]': {
          validators: {
            notEmpty: {
              message: 'Please specify at least one'
            }
          }
        },
        company: {
          validators: {
            notEmpty: {
              message: 'Please company'
            }
          }
        },
        browsers: {
          validators: {
            notEmpty: {
              message: 'Please specify at least one browser you use daily for development'
            }
          }
        }
      }
    });
  })();

  // Example Validataion Constraints
  // -------------------------------
  (function() {
    $('#exampleConstraintsForm, #exampleConstraintsFormTypes').formValidation({
      framework: "bootstrap",
      icon: null,
      fields: {
        type_email: {
          validators: {
            emailAddress: {
              message: 'The email address is not valid'
            }
          }
        },
        type_url: {
          validators: {
            url: {
              message: 'The url is not valid'
            }
          }
        },
        type_digits: {
          validators: {
            digits: {
              message: 'The value is not digits'
            }
          }
        },
        type_numberic: {
          validators: {
            integer: {
              message: 'The value is not an number'
            }
          }
        },
        type_phone: {
          validators: {
            phone: {
              message: 'The value is not an phone(US)'
            }
          }
        },
        type_credit_card: {
          validators: {
            creditCard: {
              message: 'The credit card number is not valid'
            }
          }
        },
        type_date: {
          validators: {
            date: {
              format: 'YYYY/MM/DD'
            }
          }
        },
        type_color: {
          validators: {
            color: {
              type: ['hex', 'hsl', 'hsla', 'keyword', 'rgb', 'rgba'], // The default value for type
              message: 'The value is not valid color'
            }
          }
        },
        type_ip: {
          validators: {
            ip: {
              ipv4: true,
              ipv6: true,
              message: 'The value is not valid ip(v4 or v6)'
            }
          }
        }
      }
    });
  })();

  // Example Validataion Standard Mode
  // ---------------------------------
  (function() {
    $('#exampleStandardForm').formValidation({
      framework: "bootstrap",
      button: {
        selector: '#validateButton2',
        disabled: 'disabled'
      },
      icon: null,
      fields: {
        standard_fullName: {
          validators: {
            notEmpty: {
              message: 'The full name is required and cannot be empty'
            }
          }
        },
        standard_email: {
          validators: {
            notEmpty: {
              message: 'The email address is required and cannot be empty'
            },
            emailAddress: {
              message: 'The email address is not valid'
            }
          }
        },
        standard_content: {
          validators: {
            notEmpty: {
              message: 'The content is required and cannot be empty'
            },
            stringLength: {
              max: 500,
              message: 'The content must be less than 500 characters long'
            }
          }
        }
      }
    });
  })();

  // Example Validataion Summary Mode
  // -------------------------------
  (function() {
    $('.summary-errors').hide();

    $('#exampleSummaryForm').formValidation({
      framework: "bootstrap",
      button: {
        selector: '#validateButton3',
        disabled: 'disabled'
      },
      icon: null,
      fields: {
        summary_fullName: {
          validators: {
            notEmpty: {
              message: 'The full name is required and cannot be empty'
            }
          }
        },
        summary_email: {
          validators: {
            notEmpty: {
              message: 'The email address is required and cannot be empty'
            },
            emailAddress: {
              message: 'The email address is not valid'
            }
          }
        },
        summary_content: {
          validators: {
            notEmpty: {
              message: 'The content is required and cannot be empty'
            },
            stringLength: {
              max: 500,
              message: 'The content must be less than 500 characters long'
            }
          }
        }
      }
    })

    .on('success.form.fv', function(e) {
      // Reset the message element when the form is valid
      $('.summary-errors').html('');
    })

    .on('err.field.fv', function(e, data) {
      // data.fv     --> The FormValidation instance
      // data.field  --> The field name
      // data.element --> The field element
      $('.summary-errors').show();

      // Get the messages of field
      var messages = data.fv.getMessages(data.element);

      // Remove the field messages if they're already available
      $('.summary-errors').find('li[data-field="' + data.field + '"]').remove();

      // Loop over the messages
      for (var i in messages) {
        // Create new 'li' element to show the message
        $('<li/>')
          .attr('data-field', data.field)
          .wrapInner(
            $('<a/>')
            .attr('href', 'javascript: void(0);')
            // .addClass('alert alert-danger alert-dismissible')
            .html(messages[i])
            .on('click', function(e) {
              // Focus on the invalid field
              data.element.focus();
            })
          ).appendTo('.summary-errors > ul');
      }

      // Hide the default message
      // $field.data('fv.messages') returns the default element containing the messages
      data.element
        .data('fv.messages')
        .find('.help-block[data-fv-for="' + data.field + '"]')
        .hide();
    })

    .on('success.field.fv', function(e, data) {
      // Remove the field messages
      $('.summary-errors > ul').find('li[data-field="' + data.field + '"]').remove();
      if ($('#exampleSummaryForm').data('formValidation').isValid()) {
        $('.summary-errors').hide();
      }
    });
  })();
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Wizard Form
  // -------------------
  (function() {
    // set up formvalidation
    $('#exampleAccountForm').formValidation({
      framework: 'bootstrap',
      fields: {
        username: {
          validators: {
            notEmpty: {
              message: 'The username is required'
            },
            stringLength: {
              min: 6,
              max: 30,
              message: 'The username must be more than 6 and less than 30 characters long'
            },
            regexp: {
              regexp: /^[a-zA-Z0-9_\.]+$/,
              message: 'The username can only consist of alphabetical, number, dot and underscore'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: 'The password is required'
            },
            different: {
              field: 'username',
              message: 'The password cannot be the same as username'
            }
          }
        }
      }
    });

    $("#exampleBillingForm").formValidation({
      framework: 'bootstrap',
      fields: {
        number: {
          validators: {
            notEmpty: {
              message: 'The credit card number is required'
            }
            // creditCard: {
            //   message: 'The credit card number is not valid'
            // }
          }
        },
        cvv: {
          validators: {
            notEmpty: {
              message: 'The CVV number is required'
            }
            // cvv: {
            //   creditCardField: 'number',
            //   message: 'The CVV number is not valid'
            // }
          }
        }
      }
    });

    // init the wizard
    var defaults = $.components.getDefaults("wizard");
    var options = $.extend(true, {}, defaults, {
      buttonsAppendTo: '.panel-body'
    });

    var wizard = $("#exampleWizardForm").wizard(options).data('wizard');

    // setup validator
    // http://formvalidation.io/api/#is-valid
    wizard.get("#exampleAccount").setValidator(function() {
      var fv = $("#exampleAccountForm").data('formValidation');
      fv.validate();

      if (!fv.isValid()) {
        return false;
      }

      return true;
    });

    wizard.get("#exampleBilling").setValidator(function() {
      var fv = $("#exampleBillingForm").data('formValidation');
      fv.validate();

      if (!fv.isValid()) {
        return false;
      }

      return true;
    });
  })();


  // Example Wizard Form Container
  // -----------------------------
  // http://formvalidation.io/api/#is-valid-container
  (function() {
    var defaults = $.components.getDefaults("wizard");
    var options = $.extend(true, {}, defaults, {
      onInit: function() {
        $('#exampleFormContainer').formValidation({
          framework: 'bootstrap',
          fields: {
            username: {
              validators: {
                notEmpty: {
                  message: 'The username is required'
                }
              }
            },
            password: {
              validators: {
                notEmpty: {
                  message: 'The password is required'
                }
              }
            },
            number: {
              validators: {
                notEmpty: {
                  message: 'The credit card number is not valid'
                }
              }
            },
            cvv: {
              validators: {
                notEmpty: {
                  message: 'The CVV number is required'
                }
              }
            }
          }
        });
      },
      validator: function() {
        var fv = $('#exampleFormContainer').data('formValidation');

        var $this = $(this);

        // Validate the container
        fv.validateContainer($this);

        var isValidStep = fv.isValidContainer($this);
        if (isValidStep === false || isValidStep === null) {
          return false;
        }

        return true;
      },
      onFinish: function() {
        // $('#exampleFormContainer').submit();
      },
      buttonsAppendTo: '.panel-body'
    });

    $("#exampleWizardFormContainer").wizard(options);
  })();

  // Example Wizard Pager
  // --------------------------
  (function() {
    var defaults = $.components.getDefaults("wizard");

    var options = $.extend(true, {}, defaults, {
      step: '.wizard-pane',
      templates: {
        buttons: function() {
          var options = this.options;
          var html = '<div class="btn-group btn-group-sm btn-group-flat">' +
            '<a class="btn btn-default" href="#' + this.id + '" data-wizard="back" role="button">' + options.buttonLabels.back + '</a>' +
            '<a class="btn btn-success pull-right" href="#' + this.id + '" data-wizard="finish" role="button">' + options.buttonLabels.finish + '</a>' +
            '<a class="btn btn-default pull-right" href="#' + this.id + '" data-wizard="next" role="button">' + options.buttonLabels.next + '</a>' +
            '</div>';
          return html;
        }
      },
      buttonLabels: {
        next: '<i class="icon md-chevron-right" aria-hidden="true"></i>',
        back: '<i class="icon md-chevron-left" aria-hidden="true"></i>',
        finish: '<i class="icon md-check" aria-hidden="true"></i>'
      },

      buttonsAppendTo: '.panel-actions'
    });

    $("#exampleWizardPager").wizard(options);
  })();

  // Example Wizard Progressbar
  // --------------------------
  (function() {
    var defaults = $.components.getDefaults("wizard");

    var options = $.extend(true, {}, defaults, {
      step: '.wizard-pane',
      onInit: function() {
        this.$progressbar = this.$element.find('.progress-bar').addClass('progress-bar-striped');
      },
      onBeforeShow: function(step) {
        step.$element.tab('show');
      },
      onFinish: function() {
        this.$progressbar.removeClass('progress-bar-striped').addClass('progress-bar-success');
      },
      onAfterChange: function(prev, step) {
        var total = this.length();
        var current = step.index + 1;
        var percent = (current / total) * 100;

        this.$progressbar.css({
          width: percent + '%'
        }).find('.sr-only').text(current + '/' + total);
      },
      buttonsAppendTo: '.panel-body'
    });

    $("#exampleWizardProgressbar").wizard(options);
  })();

  // Example Wizard Tabs
  // -------------------
  (function() {
    var defaults = $.components.getDefaults("wizard");
    var options = $.extend(true, {}, defaults, {
      step: '> .nav > li > a',
      onBeforeShow: function(step) {
        step.$element.tab('show');
      },
      classes: {
        step: {
          //done: 'color-done',
          error: 'color-error'
        }
      },
      onFinish: function() {
        alert('finish');
      },
      buttonsAppendTo: '.tab-content'
    });

    $("#exampleWizardTabs").wizard(options);
  })();

  // Example Wizard Accordion
  // ------------------------
  (function() {
    var defaults = $.components.getDefaults("wizard");
    var options = $.extend(true, {}, defaults, {
      step: '.panel-title[data-toggle="collapse"]',
      classes: {
        step: {
          //done: 'color-done',
          error: 'color-error'
        }
      },
      templates: {
        buttons: function() {
          return '<div class="panel-footer">' + defaults.templates.buttons.call(this) + '</div>';
        }
      },
      onBeforeShow: function(step) {
        step.$pane.collapse('show');
      },

      onBeforeHide: function(step) {
        step.$pane.collapse('hide');
      },

      onFinish: function() {
        alert('finish');
      },

      buttonsAppendTo: '.panel-collapse'
    });

    $("#exampleWizardAccordion").wizard(options);
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    var $example = $('#exampleTransition');

    $(document).on('click.panel.transition', '[data-type]', function() {
      var type = $(this).data('type');

      $example.data('animateList').run(type);
    });

    $(document).on('close.uikit.panel', '[class*=blocks-] > li > .panel', function() {
      $(this).parent().hide();
    });
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Treeview
  // ---------
  (function() {
    var data = [{
      text: 'assets',
      href: '#assets',
      state: {
        expanded: false
      },
      nodes: [{
        text: 'css',
        href: '#css',
        nodes: [{
          text: 'bootstrap.css',
          href: '#bootstrap.css',
          icon: 'fa fa-file-code-o'
        }, {
          text: 'site.css',
          href: '#site.css',
          icon: 'fa fa-file-code-o'
        }]
      }, {
        text: 'fonts',
        href: '#fonts',
        nodes: [{
          text: 'font-awesome',
          href: '#font-awesome'
        }, {
          text: 'web-icons',
          href: '#web-icons'
        }]
      }, {
        text: 'images',
        href: '#images',
        nodes: [{
          text: 'logo.png',
          href: '#logo.png',
          icon: 'fa fa-file-photo-o'
        }, {
          text: 'bg.png',
          href: '#bg.png',
          icon: 'fa fa-file-photo-o'
        }]
      }]
    }, {
      text: 'grunt',
      href: '#grunt',
      state: {
        expanded: false
      },
      nodes: [{
        text: 'autoprefixer.js',
        href: '#autoprefixer.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'clean.js',
        href: '#clean.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'concat.js',
        href: '#concat.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'csscomb.js',
        href: '#csscomb.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'cssmin.js',
        href: '#cssmin.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'less.js',
        href: '#less.js',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'uglify.js',
        href: '#uglify.js',
        icon: 'fa fa-file-code-o'
      }]
    }, {
      text: 'html',
      href: '#html',
      state: {
        expanded: true
      },
      nodes: [{
        text: 'blog.html',
        href: '#blog.html',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'docs.html',
        href: '#docs.html',
        icon: 'fa fa-file-code-o'
      }, {
        text: 'index.html',
        href: '#index.html',
        state: {
          selected: true
        },
        icon: 'fa fa-file-code-o'
      }]
    }, {
      text: 'media',
      href: '#media',
      state: {
        expanded: false
      },
      nodes: [{
        text: 'audio.mp3',
        href: '#audio.mp3',
        icon: 'fa fa-file-audio-o'
      }, {
        text: 'video.mp4',
        href: '#video.mp4',
        icon: 'fa fa-file-video-o'
      }]
    }, {
      text: 'Gruntfile.js',
      href: '#Gruntfile.js',
      icon: 'fa fa-file-code-o'
    }, {
      text: 'bower.json',
      href: '#bower.json',
      icon: 'fa fa-file-code-o'
    }, {
      text: 'README.pdf',
      href: '#README.pdf',
      icon: 'fa fa-file-pdf-o'
    }, {
      text: 'package.json',
      href: '#package.json',
      icon: 'fa fa-file-code-o'
    }];

    var defaults = $.components.getDefaults("treeview");

    var options = $.extend({}, defaults, {
      levels: 1,
      color: false,
      backColor: false,
      borderColor: false,
      onhoverColor: false,
      selectedColor: false,
      selectedBackColor: false,
      searchResultColor: false,
      searchResultBackColor: false,
      data: data,
      highlightSelected: true
    });

    $('#filesTree').treeview(options);
  })();

  // Codemirror
  // ----------
  CodeMirror.fromTextArea(document.getElementById('code'), {
    lineNumbers: !0,
    theme: 'eclipse',
    mode: 'text/html',
    scrollbarStyle: "simple"
  });

  // Contextmenu
  // -----------
  $("#filesTree").contextmenu({
    target: "#filesContextMenu"
  });

})(document, window, jQuery);

(function(document, window, $) {

  $(document).ready(function() {
    Site.run();

    if ($('.list-group[data-plugin="nav-tabs"]').length) {
      $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
        $(e.target).addClass('active').siblings().removeClass('active');
      });
    }
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    $('.md-search').magnificPopup({
      type: 'image',
      closeOnContentClick: true,
      mainClass: 'mfp-fade',
      gallery: {
        enabled: true,
        navigateByImgClick: true,
        preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
      }
    });
  });
})(document, window, jQuery);

(function(document, window, $) {

  $(document).ready(function() {
    $.components.init('appear');

    var slider_messages = $(".slider-messages"),
      slider_nav = $(".slider-nav");

    slider_messages.slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      arrows: false,
      fade: true,
      asNavFor: '.slider-nav'
    });

    slider_nav.slick({
      dots: true,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll: 4,
      focusOnSelect: true,
      asNavFor: '.slider-messages',
      responsive: [{
        breakpoint: 1200,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3,
          infinite: true,
          dots: true
        }
      }, {
        breakpoint: 1024,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2
        }
      }, {
        breakpoint: 768,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1
        }
      }]
    });
  });


  $('body').asScroll();

  $('#landingNav a').on('click', function() {
    var target = $(this).attr('href').replace('#', '');
    $('body').data('asScroll').scrollYToTarget(target);
  });

  $('#landingNav').on('hide.bs.collapse', function() {
    $(this).removeClass('nav-open');
  });
  $('#landingNav').on('show.bs.collapse', function() {
    $(this).addClass('nav-open');
  });

  var homeHeight = $('.home').height();

  function updateNav() {
    var scrollTop = window.scrollY;
    if (scrollTop > (homeHeight / 10)) {
      $('#landingNav').addClass('is-scroll');
    } else {
      $('#landingNav').removeClass('is-scroll');
    }
  }

  updateNav();
  $(window).on('scroll', function() {
    updateNav();
  });

  if (!$('html').hasClass('touch')) {
    $(window).on('scroll', function() {
      var scrollTop = $('body')[0].scrollTop;
      if (scrollTop > homeHeight) return;

      $('.home').css('background-position-y', scrollTop);
    });
  }

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;

  $(document).ready(function() {
    Site.run();

    var map = new GMaps({
      el: '#gmap',
      lat: -12.043333,
      lng: -77.028333,
      zoomControl: true,
      zoomControlOpt: {
        style: "SMALL",
        position: "TOP_LEFT"
      },
      panControl: true,
      streetViewControl: false,
      mapTypeControl: false,
      overviewMapControl: false

    });

    map.drawOverlay({
      lat: -12.043333,
      lng: -77.028333,
      content: '<i class="md-pin" style="font-size:40px;color:' + $.colors("red", 500) + ';"></i>',
    });

    map.drawOverlay({
      lat: -12.05449279282314,
      lng: -77.04333,
      content: '<i class="md-pin" style="font-size:32px;color:' + $.colors("primary", 500) + ';"></i>',
    });

    map.addStyle({
      styledMapName: "Styled Map",
      styles: $.components.get('gmaps', 'styles'),
      mapTypeId: "map_style"
    });

    map.setStyle("map_style");

    var path = [
      [-12.044012922866312, -77.02470665341184],
      [-12.05449279282314, -77.03024273281858],
      [-12.055122327623378, -77.03039293652341],
      [-12.075917129727586, -77.02764635449216],
      [-12.07635776902266, -77.02792530422971],
      [-12.076819390363665, -77.02893381481931],
      [-12.088527520066453, -77.0241058385925],
      [-12.090814532191756, -77.02271108990476]
    ];

    map.drawPolyline({
      path: path,
      strokeColor: '#131540',
      strokeOpacity: 0.6,
      strokeWeight: 6
    });
  });
})(document, window, jQuery);

var LocsA = [{
  lat: 45.9,
  lon: 10.9,
  title: 'Title A1',
  html: '<h3>Content A1</h3>',
  icon: 'http://maps.google.com/mapfiles/markerA.png',
  animation: google.maps.Animation.DROP
}, {
  lat: 44.8,
  lon: 1.7,
  title: 'Title B1',
  html: '<h3>Content B1</h3>',
  icon: 'http://maps.google.com/mapfiles/markerB.png',
  show_infowindow: false
}, {
  lat: 51.5,
  lon: -1.1,
  title: 'Title C1',
  html: [
    '<h3>Content C1</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8,
  icon: 'http://maps.google.com/mapfiles/markerC.png'
}];

var LocsAv2 = [{
  lat: 45.9,
  lon: 10.9,
  title: 'Zone A1',
  html: '<h3>Content A1</h3>',
  type: 'circle',
  circle_options: {
    radius: 200000
  },
  draggable: true
}, {
  lat: 44.8,
  lon: 1.7,
  title: 'Draggable',
  html: '<h3>Content B1</h3>',
  show_infowindow: false,
  visible: true,
  draggable: true
}, {
  lat: 51.5,
  lon: -1.1,
  title: 'Title C1',
  html: [
    '<h3>Content C1</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8,
  visible: true
}];


var LocsB = [{
  lat: 52.1,
  lon: 11.3,
  title: 'Title A2',
  html: [
    '<h3>Content A2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8
}, {
  lat: 51.2,
  lon: 22.2,
  title: 'Title B2',
  html: [
    '<h3>Content B2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8
}, {
  lat: 49.4,
  lon: 35.9,
  title: 'Title C2',
  html: [
    '<h3>Content C2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 4
}, {
  lat: 47.8,
  lon: 15.6,
  title: 'Title D2',
  html: [
    '<h3>Content D2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 6
}];


var LocsBv2 = [{
  lat: 52.1,
  lon: 11.3,
  title: 'Title A2',
  html: [
    '<h3>Content A2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8
}, {
  lat: 51.2,
  lon: 22.2,
  title: 'Title B2',
  html: [
    '<h3>Content B2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 8,
  type: 'circle',
  circle_options: {
    radius: 100000
  }
}, {
  lat: 49.4,
  lon: 35.9,
  title: 'Title C2',
  html: [
    '<h3>Content C2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 4
}, {
  lat: 47.8,
  lon: 15.6,
  title: 'Title D2',
  html: [
    '<h3>Content D2</h3>',
    '<p>Lorem Ipsum..</p>'
  ].join(''),
  zoom: 6
}];


var LocsAB = LocsA.concat(LocsB);


var LocsC = [{
  lat: 45.4654,
  lon: 9.1866,
  title: 'Milan, Italy',
  type: 'circle',
  circle_options: {
    radius: 1000
  },
  visible: false
}, {
  lat: 47.36854,
  lon: 8.53910,
  title: 'Zurich, Switzerland'
}, {
  lat: 48.892,
  lon: 2.359,
  title: 'Paris, France'
}, {
  lat: 48.13654,
  lon: 11.57706,
  title: 'Munich, Germany'
}];

var LocsD = [{
  lat: 45.4654,
  lon: 9.1866,
  title: 'Milan, Italy',
  html: '<h3>Milan, Italy</h3>'
}, {
  lat: 47.36854,
  lon: 8.53910,
  title: 'Zurich, Switzerland',
  html: '<h3>Zurich, Switzerland</h3>',
  visible: false
}, {
  lat: 48.892,
  lon: 2.359,
  title: 'Paris, France',
  html: '<h3>Paris, France</h3>',
  stopover: true
}, {
  lat: 48.13654,
  lon: 11.57706,
  title: 'Munich, Germany',
  html: '<h3>Munich, Germany</h3>'
}];

var Circles = [{
  lat: 51.51386,
  lon: -0.09559,
  title: 'Draggable marker',
  circle_options: {
    radius: 160
  },
  stroke_options: {
    strokeColor: '#aaaa00',
    fillColor: '#eeee00'
  },
  draggable: true
}, {
  lat: 51.51420,
  lon: -0.09303,
  title: 'Draggable circle',
  circle_options: {
    radius: 50
  },
  stroke_options: {
    strokeColor: '#aa0000',
    fillColor: '#ee0000'
  },
  visible: false,
  draggable: true
}, {
  lat: 51.51498,
  lon: -0.09097,
  circle_options: {
    radius: 80
  },
  visible: false,
  draggable: true
}, {
  lat: 51.51328,
  lon: -0.09021,
  circle_options: {
    radius: 160,
    editable: true
  },
  title: 'Editable circle',
  html: 'Change my size',
  visible: false,
  draggable: true
}, {
  lat: 51.51211,
  lon: -0.09050,
  circle_options: {
    radius: 130
  },
  stroke_options: {
    strokeColor: '#00aa00',
    fillColor: '#00aa00'
  },
  visible: false
}, {
  lat: 51.51226,
  lon: -0.09435,
  circle_options: {
    radius: 100
  },
  draggable: true
}, {
  lat: 51.513,
  lon: -0.08410,
  type: 'marker',
  title: 'Simple marker',
  html: 'I\'m a simple marker.'
}];

(function(document, window, $) {
  'use strict';
  var Site = window.Site;

  $(document).ready(function() {
    Site.run();

    var defaults = $.components.getDefaults('vectorMap');
    var options = $.extend({}, defaults, {
      markers: [{
        latLng: [1.3, 103.8],
        name: '940 Visits'
      }, {
        latLng: [51.511214, -0.119824],
        name: '530 Visits'
      }, {
        latLng: [40.714353, -74.005973],
        name: '340 Visits'
      }, {
        latLng: [-22.913395, -43.200710],
        name: '1,800 Visits'
      }]
    }, true);

    $('#world-map').vectorMap(options);
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    $('.timeline-item').appear();

    $('.timeline-item').not(':appeared').each(function() {
      var $item = $(this);
      $item.addClass('timeline-invisible');
      $item.find('.timeline-dot').addClass('invisible');
      $item.find('.timeline-info').addClass('invisible');
      $item.find('.timeline-content').addClass('invisible');
    });

    $(document).on('appear', '.timeline-item.timeline-invisible', function(e) {
      var $item = $(this);
      $item.removeClass('timeline-invisible');

      $item.find('.timeline-dot').removeClass('invisible').addClass('animation-scale-up');

      if ($item.hasClass('timeline-reverse') || $item.css('float') === 'none') {
        $item.find('.timeline-info').removeClass('invisible').addClass('animation-slide-right');
        $item.find('.timeline-content').removeClass('invisible').addClass('animation-slide-right');
      } else {
        $item.find('.timeline-info').removeClass('invisible').addClass('animation-slide-left');
        $item.find('.timeline-content').removeClass('invisible').addClass('animation-slide-left');
      }
    });
  });
})(document, window, jQuery);

function cellStyle(value, row, index) {
  var classes = ['active', 'success', 'info', 'warning', 'danger'];

  if (index % 2 === 0 && index / 2 < classes.length) {
    return {
      classes: classes[index / 2]
    };
  }
  return {};
}

function rowStyle(row, index) {
  var classes = ['active', 'success', 'info', 'warning', 'danger'];

  if (index % 2 === 0 && index / 2 < classes.length) {
    return {
      classes: classes[index / 2]
    };
  }
  return {};
}

function scoreSorter(a, b) {
  if (a > b) return 1;
  if (a < b) return -1;
  return 0;
}

function nameFormatter(value) {
  return value + '<i class="icon md-book" aria-hidden="true"></i> ';
}

function starsFormatter(value) {
  return '<i class="icon md-star" aria-hidden="true"></i> ' + value;
}

function queryParams() {
  return {
    type: 'owner',
    sort: 'updated',
    direction: 'desc',
    per_page: 100,
    page: 1
  };
}

function buildTable($el, cells, rows) {
  var i, j, row,
    columns = [],
    data = [];

  for (i = 0; i < cells; i++) {
    columns.push({
      field: 'field' + i,
      title: 'Cell' + i
    });
  }
  for (i = 0; i < rows; i++) {
    row = {};
    for (j = 0; j < cells; j++) {
      row['field' + j] = 'Row-' + i + '-' + j;
    }
    data.push(row);
  }
  $el.bootstrapTable('destroy').bootstrapTable({
    columns: columns,
    data: data,
    iconsPrefix: 'icon',
    iconSize: 'icon',
    icons: {
      columns: 'md-view-list-alt'
    }
  });
}

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Bootstrap Table From Data
  // ---------------------------------
  (function() {
    var bt_data = [{
      "Tid": "1",
      "First": "Jill",
      "Last": "Smith",
      "Score": "50"
    }, {
      "Tid": "2",
      "First": "Eve",
      "Last": "Jackson",
      "Score": "94"
    }, {
      "Tid": "3",
      "First": "John",
      "Last": "Doe",
      "Score": "80"
    }, {
      "Tid": "4",
      "First": "Adam",
      "Last": "Johnson",
      "Score": "67"
    }, {
      "Tid": "5",
      "First": "Fish",
      "Last": "Johnson",
      "Score": "100"
    }, {
      "Tid": "6",
      "First": "CC",
      "Last": "Joson",
      "Score": "77"
    }, {
      "Tid": "7",
      "First": "Piger",
      "Last": "Yoson",
      "Score": "87"
    }];


    $('#exampleTableFromData').bootstrapTable({
      data: bt_data,
      // mobileResponsive: true,
      height: "250"
    });
  })();

  // Example Bootstrap Table Columns
  // -------------------------------
  (function() {
    $('#exampleTableColumns').bootstrapTable({
      url: "../../assets/data/bootstrap_table_test.json",
      height: "400",
      showColumns: true,
      iconsPrefix: 'icon',
      iconSize: 'icon',
      icons: {
        refresh: 'md-refresh',
        toggle: 'md-receipt',
        columns: 'md-view-list-alt'
      }
    });
  })();


  // Example Bootstrap Table Large Columns
  // -------------------------------------
  buildTable($('#exampleTableLargeColumns'), 50, 50);


  // Example Bootstrap Table Toolbar
  // -------------------------------
  (function() {
    $('#exampleTableToolbar').bootstrapTable({
      url: "../../assets/data/bootstrap_table_test2.json",
      search: true,
      showRefresh: true,
      showToggle: true,
      showColumns: true,
      toolbar: '#exampleToolbar',
      iconsPrefix: 'icon',
      iconSize: 'icon',
      icons: {
        refresh: 'md-refresh',
        toggle: 'md-receipt',
        columns: 'md-view-list-alt'
      }
    });
  })();


  // Example Bootstrap Table Events
  // ------------------------------
  (function() {
    $('#exampleTableEvents').bootstrapTable({
      url: "../../assets/data/bootstrap_table_test.json",
      search: true,
      pagination: true,
      showRefresh: true,
      showToggle: true,
      showColumns: true,
      toolbar: '#exampleTableEventsToolbar',
      iconsPrefix: 'icon',
      icons: {
        refresh: 'md-refresh',
        toggle: 'md-receipt',
        columns: 'md-view-list-alt'
      }
    });

    var $result = $('#examplebtTableEventsResult');

    $('#exampleTableEvents').on('all.bs.table', function(e, name, args) {
        console.log('Event:', name, ', data:', args);
      })
      .on('click-row.bs.table', function(e, row, $element) {
        $result.text('Event: click-row.bs.table');
      })
      .on('dbl-click-row.bs.table', function(e, row, $element) {
        $result.text('Event: dbl-click-row.bs.table');
      })
      .on('sort.bs.table', function(e, name, order) {
        $result.text('Event: sort.bs.table');
      })
      .on('check.bs.table', function(e, row) {
        $result.text('Event: check.bs.table');
      })
      .on('uncheck.bs.table', function(e, row) {
        $result.text('Event: uncheck.bs.table');
      })
      .on('check-all.bs.table', function(e) {
        $result.text('Event: check-all.bs.table');
      })
      .on('uncheck-all.bs.table', function(e) {
        $result.text('Event: uncheck-all.bs.table');
      })
      .on('load-success.bs.table', function(e, data) {
        $result.text('Event: load-success.bs.table');
      })
      .on('load-error.bs.table', function(e, status) {
        $result.text('Event: load-error.bs.table');
      })
      .on('column-switch.bs.table', function(e, field, checked) {
        $result.text('Event: column-switch.bs.table');
      })
      .on('page-change.bs.table', function(e, size, number) {
        $result.text('Event: page-change.bs.table');
      })
      .on('search.bs.table', function(e, text) {
        $result.text('Event: search.bs.table');
      });
  })();
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;


  $(document).ready(function($) {
    Site.run();
  });

  // Fixed Header Example
  // --------------------
  (function() {
    var offsetTop = 0;
    if ($('.site-navbar').length > 0) {
      offsetTop = $('.site-navbar').eq(0).innerHeight() + $('.site-menubar').eq(0).innerHeight();
    }

    // initialize datatable
    var table = $('#exampleFixedHeader').DataTable({
      responsive: true,
      fixedHeader: {
        header: true,
        headerOffset: offsetTop
      },
      "bPaginate": false,
      "sDom": "t" // just show table, no other controls
    });



    // redraw fixedHeaders as necessary
    // $(window).resize(function() {
    //   fixedHeader._fnUpdateClones(true);
    //   fixedHeader._fnUpdatePositions();
    // });
  })();

  // Individual column searching
  // ---------------------------
  (function() {
    $(document).ready(function() {
      var defaults = $.components.getDefaults("dataTable");

      var options = $.extend(true, {}, defaults, {
        initComplete: function() {
          this.api().columns().every(function() {
            var column = this;
            var select = $('<select class="form-control width-full"><option value=""></option></select>')
              .appendTo($(column.footer()).empty())
              .on('change', function() {
                var val = $.fn.dataTable.util.escapeRegex(
                  $(this).val()
                );

                column
                  .search(val ? '^' + val + '$' : '', true, false)
                  .draw();
              });

            column.data().unique().sort().each(function(d, j) {
              select.append('<option value="' + d + '">' + d + '</option>')
            });
          });
        }
      });

      $('#exampleTableSearch').DataTable(options);
    });
  })();

  // Table Tools
  // -----------
  (function() {

    $(document).ready(function() {
      var defaults = $.components.getDefaults("dataTable");

      var options = $.extend(true, {}, defaults, {
        "aoColumnDefs": [{
          'bSortable': false,
          'aTargets': [-1]
        }],
        "iDisplayLength": 5,
        "aLengthMenu": [
          [5, 10, 25, 50, -1],
          [5, 10, 25, 50, "All"]
        ],
        "sDom": '<"dt-panelmenu clearfix"Tfr>t<"dt-panelfooter clearfix"ip>',
        "oTableTools": {
          "sSwfPath": "../../../global/vendor/datatables-tabletools/swf/copy_csv_xls_pdf.swf"
        }
      });

      $('#exampleTableTools').dataTable(options);
    });
  })();

  // Table Add Row
  // -------------
  (function($) {
    var EditableTable = {

      options: {
        addButton: '#addToTable',
        table: '#exampleAddRow',
        dialog: {
          wrapper: '#dialog',
          cancelButton: '#dialogCancel',
          confirmButton: '#dialogConfirm',
        }
      },

      initialize: function() {
        this
          .setVars()
          .build()
          .events();
      },

      setVars: function() {
        this.$table = $(this.options.table);
        this.$addButton = $(this.options.addButton);

        // dialog
        this.dialog = {};
        this.dialog.$wrapper = $(this.options.dialog.wrapper);
        this.dialog.$cancel = $(this.options.dialog.cancelButton);
        this.dialog.$confirm = $(this.options.dialog.confirmButton);

        return this;
      },

      build: function() {
        this.datatable = this.$table.DataTable({
          aoColumns: [
            null,
            null,
            null, {
              "bSortable": false
            }
          ],
          language: {
            "sSearchPlaceholder": "Search..",
            "lengthMenu": "_MENU_",
            "search": "_INPUT_"
          }
        });

        window.dt = this.datatable;

        return this;
      },

      events: function() {
        var _self = this;

        this.$table
          .on('click', 'a.save-row', function(e) {
            e.preventDefault();

            _self.rowSave($(this).closest('tr'));
          })
          .on('click', 'a.cancel-row', function(e) {
            e.preventDefault();

            _self.rowCancel($(this).closest('tr'));
          })
          .on('click', 'a.edit-row', function(e) {
            e.preventDefault();

            _self.rowEdit($(this).closest('tr'));
          })
          .on('click', 'a.remove-row', function(e) {
            e.preventDefault();

            var $row = $(this).closest('tr');
            bootbox.dialog({
              message: "Are you sure that you want to delete this row?",
              title: "ARE YOU SURE?",
              buttons: {
                danger: {
                  label: "Confirm",
                  className: "btn-danger",
                  callback: function() {
                    _self.rowRemove($row);
                  }
                },
                main: {
                  label: "Cancel",
                  className: "btn-primary",
                  callback: function() {}
                }
              }
            });
          });

        this.$addButton.on('click', function(e) {
          e.preventDefault();

          _self.rowAdd();
        });

        this.dialog.$cancel.on('click', function(e) {
          e.preventDefault();
          $.magnificPopup.close();
        });

        return this;
      },


      // =============
      // ROW FUNCTIONS
      // =============
      rowAdd: function() {
        this.$addButton.attr({
          'disabled': 'disabled'
        });

        var actions,
          data,
          $row;

        actions = [
          '<a href="#" class="btn btn-sm btn-icon btn-pure btn-default hidden on-editing save-row" data-toggle="tooltip" data-original-title="Save"><i class="icon md-wrench" aria-hidden="true"></i></a>',
          '<a href="#" class="btn btn-sm btn-icon btn-pure btn-default hidden on-editing cancel-row" data-toggle="tooltip" data-original-title="Delete"><i class="icon md-close" aria-hidden="true"></i></a>',
          '<a href="#" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row" data-toggle="tooltip" data-original-title="Edit"><i class="icon md-edit" aria-hidden="true"></i></a>',
          '<a href="#" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row" data-toggle="tooltip" data-original-title="Remove"><i class="icon md-delete" aria-hidden="true"></i></a>'
        ].join(' ');

        data = this.datatable.row.add(['', '', '', actions]);
        $row = this.datatable.row(data[0]).nodes().to$();

        $row
          .addClass('adding')
          .find('td:last')
          .addClass('actions');

        this.rowEdit($row);

        this.datatable.order([0, 'asc']).draw(); // always show fields
      },

      rowCancel: function($row) {
        var _self = this,
          $actions,
          i,
          data;

        if ($row.hasClass('adding')) {
          this.rowRemove($row);
        } else {

          data = this.datatable.row($row.get(0)).data();
          this.datatable.row($row.get(0)).data(data);

          $actions = $row.find('td.actions');
          if ($actions.get(0)) {
            this.rowSetActionsDefault($row);
          }

          this.datatable.draw();
        }
      },

      rowEdit: function($row) {
        var _self = this,
          data;

        data = this.datatable.row($row.get(0)).data();

        $row.children('td').each(function(i) {
          var $this = $(this);

          if ($this.hasClass('actions')) {
            _self.rowSetActionsEditing($row);
          } else {
            $this.html('<input type="text" class="form-control input-block" value="' + data[i] + '"/>');
          }
        });
      },

      rowSave: function($row) {
        var _self = this,
          $actions,
          values = [];

        if ($row.hasClass('adding')) {
          this.$addButton.removeAttr('disabled');
          $row.removeClass('adding');
        }

        values = $row.find('td').map(function() {
          var $this = $(this);

          if ($this.hasClass('actions')) {
            _self.rowSetActionsDefault($row);
            return _self.datatable.cell(this).data();
          } else {
            return $.trim($this.find('input').val());
          }
        });

        this.datatable.row($row.get(0)).data(values);

        $actions = $row.find('td.actions');
        if ($actions.get(0)) {
          this.rowSetActionsDefault($row);
        }

        this.datatable.draw();
      },

      rowRemove: function($row) {
        if ($row.hasClass('adding')) {
          this.$addButton.removeAttr('disabled');
        }

        this.datatable.row($row.get(0)).remove().draw();
      },

      rowSetActionsEditing: function($row) {
        $row.find('.on-editing').removeClass('hidden');
        $row.find('.on-default').addClass('hidden');
      },

      rowSetActionsDefault: function($row) {
        $row.find('.on-editing').addClass('hidden');
        $row.find('.on-default').removeClass('hidden');
      }

    };

    $(function() {
      EditableTable.initialize();
    });

  }).apply(this, [jQuery]);


})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example editable Table
  // ----------------------
  $('#editableTable').editableTableWidget().numericInputExample().find('td:first').focus();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Row Toggler
  // -------------------
  (function() {
    $('#exampleRowToggler').footable();
  })();

  // Expand / Collapse All Rows
  // --------------------------
  (function() {
    var fooColExp = $('#exampleAccordion');
    fooColExp.footable().trigger('footable_expand_first_row');


    $('#exampleCollapseBtn').on('click', function() {
      fooColExp.trigger('footable_collapse_all');
    });
    $('#exampleExpandBtn').on('click', function() {
      fooColExp.trigger('footable_expand_all');
    })
  })();

  // Accordion
  // ---------
  (function() {
    $('#exampleFooAccordion').footable().on('footable_row_expanded', function(e) {
      $('#exampleFooAccordion tbody tr.footable-detail-show').not(e.row).each(function() {
        $('#exampleFooAccordion').data('footable').toggleDetail(this);
      });
    });
  })();

  // Pagination
  // ----------
  (function() {
    $('#examplePagination').footable();
    $('#exampleShow').change(function(e) {
      e.preventDefault();
      var pagesize = $(this).find('option:selected').val();
      $('#examplePagination').data('page-size', pagesize);
      $('#examplePagination').trigger('footable_initialized');
    });
  })();

  // Filtering
  // ---------
  (function() {
    var filtering = $('#exampleFootableFiltering');
    filtering.footable().on('footable_filtering', function(e) {
      var selected = $('#filteringStatus').find(':selected').val();
      e.filter += (e.filter && e.filter.length > 0) ? ' ' + selected : selected;
      e.clear = !e.filter;
    });

    // Filter status
    $('#filteringStatus').change(function(e) {
      e.preventDefault();
      filtering.trigger('footable_filter', {
        filter: $(this).val()
      });
    });

    // Search input
    $('#filteringSearch').on('input', function(e) {
      e.preventDefault();
      filtering.trigger('footable_filter', {
        filter: $(this).val()
      });
    });
  })();

  // Add & Remove Row
  // ----------------
  (function() {
    var addrow = $('#exampleFooAddRemove');
    addrow.footable().on('click', '.delete-row-btn', function() {

      //get the footable object
      var footable = addrow.data('footable');

      //get the row we are wanting to delete
      var row = $(this).parents('tr:first');

      //delete the row
      footable.removeRow(row);
    });

    // Search input
    $('#addRemoveSearch').on('input', function(e) {
      e.preventDefault();

      addrow.trigger('footable_filter', {
        filter: $(this).val()
      });
    });

    // Add Row Button
    $('#addRowBtn').click(function() {

      //get the footable object
      var footable = addrow.data('footable');

      //build up the row we are wanting to add
      var newRow = '<tr><td>Adam</td><td>Doe</td><td>Traffic Court Referee</td><td>22 Jun 1972</td><td><span class="label label-table label-success">Active</span></td><td><button type="button" class="btn btn-sm btn-icon btn-pure btn-default delete-row-btn" data-toggle="tooltip" data-original-title="Delete"><i class="icon md-close" aria-hidden="true"></i></button></td></tr>';

      //add it
      footable.appendRow(newRow);
    });
  })();

})(document, window, jQuery);

(function() {

  var db = {

    loadData: function(filter) {
      return $.grep(this.clients, function(client) {
        return (!filter.Name || client.Name.indexOf(filter.Name) > -1) && (!filter.Age || client.Age === filter.Age) && (!filter.Address || client.Address.indexOf(filter.Address) > -1) && (!filter.Country || client.Country === filter.Country) && (filter.Married === undefined || client.Married === filter.Married);
      });
    },

    insertItem: function(insertingClient) {
      this.clients.push(insertingClient);
    },

    updateItem: function(updatingClient) {},

    deleteItem: function(deletingClient) {
      var clientIndex = $.inArray(deletingClient, this.clients);
      this.clients.splice(clientIndex, 1);
    }

  };

  window.db = db;


  db.countries = [{
    Name: "",
    Id: 0
  }, {
    Name: "United States",
    Id: 1
  }, {
    Name: "Canada",
    Id: 2
  }, {
    Name: "United Kingdom",
    Id: 3
  }, {
    Name: "France",
    Id: 4
  }, {
    Name: "Brazil",
    Id: 5
  }, {
    Name: "China",
    Id: 6
  }, {
    Name: "Russia",
    Id: 7
  }];

  db.clients = [{
    "Name": "Otto Clay",
    "Age": 61,
    "Country": 6,
    "Address": "Ap #897-1459 Quam Avenue",
    "Married": false
  }, {
    "Name": "Connor Johnston",
    "Age": 73,
    "Country": 7,
    "Address": "Ap #370-4647 Dis Av.",
    "Married": false
  }, {
    "Name": "Lacey Hess",
    "Age": 29,
    "Country": 7,
    "Address": "Ap #365-8835 Integer St.",
    "Married": false
  }, {
    "Name": "Timothy Henson",
    "Age": 78,
    "Country": 1,
    "Address": "911-5143 Luctus Ave",
    "Married": false
  }, {
    "Name": "Ramona Benton",
    "Age": 43,
    "Country": 5,
    "Address": "Ap #614-689 Vehicula Street",
    "Married": true
  }, {
    "Name": "Ezra Tillman",
    "Age": 51,
    "Country": 1,
    "Address": "P.O. Box 738, 7583 Quisque St.",
    "Married": true
  }, {
    "Name": "Dante Carter",
    "Age": 59,
    "Country": 1,
    "Address": "P.O. Box 976, 6316 Lorem, St.",
    "Married": false
  }, {
    "Name": "Christopher Mcclure",
    "Age": 58,
    "Country": 1,
    "Address": "847-4303 Dictum Av.",
    "Married": true
  }, {
    "Name": "Ruby Rocha",
    "Age": 62,
    "Country": 2,
    "Address": "5212 Sagittis Ave",
    "Married": false
  }, {
    "Name": "Imelda Hardin",
    "Age": 39,
    "Country": 5,
    "Address": "719-7009 Auctor Av.",
    "Married": false
  }, {
    "Name": "Jonah Johns",
    "Age": 28,
    "Country": 5,
    "Address": "P.O. Box 939, 9310 A Ave",
    "Married": false
  }, {
    "Name": "Herman Rosa",
    "Age": 49,
    "Country": 7,
    "Address": "718-7162 Molestie Av.",
    "Married": true
  }, {
    "Name": "Arthur Gay",
    "Age": 20,
    "Country": 7,
    "Address": "5497 Neque Street",
    "Married": false
  }, {
    "Name": "Xena Wilkerson",
    "Age": 63,
    "Country": 1,
    "Address": "Ap #303-6974 Proin Street",
    "Married": true
  }, {
    "Name": "Lilah Atkins",
    "Age": 33,
    "Country": 5,
    "Address": "622-8602 Gravida Ave",
    "Married": true
  }, {
    "Name": "Malik Shepard",
    "Age": 59,
    "Country": 1,
    "Address": "967-5176 Tincidunt Av.",
    "Married": false
  }, {
    "Name": "Keely Silva",
    "Age": 24,
    "Country": 1,
    "Address": "P.O. Box 153, 8995 Praesent Ave",
    "Married": false
  }, {
    "Name": "Hunter Pate",
    "Age": 73,
    "Country": 7,
    "Address": "P.O. Box 771, 7599 Ante, Road",
    "Married": false
  }, {
    "Name": "Mikayla Roach",
    "Age": 55,
    "Country": 5,
    "Address": "Ap #438-9886 Donec Rd.",
    "Married": true
  }, {
    "Name": "Upton Joseph",
    "Age": 48,
    "Country": 4,
    "Address": "Ap #896-7592 Habitant St.",
    "Married": true
  }, {
    "Name": "Jeanette Pate",
    "Age": 59,
    "Country": 2,
    "Address": "P.O. Box 177, 7584 Amet, St.",
    "Married": false
  }, {
    "Name": "Kaden Hernandez",
    "Age": 79,
    "Country": 3,
    "Address": "366 Ut St.",
    "Married": true
  }, {
    "Name": "Kenyon Stevens",
    "Age": 20,
    "Country": 3,
    "Address": "P.O. Box 704, 4580 Gravida Rd.",
    "Married": false
  }, {
    "Name": "Jerome Harper",
    "Age": 31,
    "Country": 5,
    "Address": "2464 Porttitor Road",
    "Married": false
  }, {
    "Name": "Jelani Patel",
    "Age": 36,
    "Country": 2,
    "Address": "P.O. Box 541, 5805 Nec Av.",
    "Married": true
  }, {
    "Name": "Keaton Oconnor",
    "Age": 21,
    "Country": 1,
    "Address": "Ap #657-1093 Nec, Street",
    "Married": false
  }, {
    "Name": "Bree Johnston",
    "Age": 31,
    "Country": 2,
    "Address": "372-5942 Vulputate Avenue",
    "Married": false
  }, {
    "Name": "Maisie Hodges",
    "Age": 70,
    "Country": 7,
    "Address": "P.O. Box 445, 3880 Odio, Rd.",
    "Married": false
  }, {
    "Name": "Kuame Calhoun",
    "Age": 39,
    "Country": 2,
    "Address": "P.O. Box 609, 4105 Rutrum St.",
    "Married": true
  }, {
    "Name": "Carlos Cameron",
    "Age": 38,
    "Country": 5,
    "Address": "Ap #215-5386 A, Avenue",
    "Married": false
  }, {
    "Name": "Fulton Parsons",
    "Age": 25,
    "Country": 7,
    "Address": "P.O. Box 523, 3705 Sed Rd.",
    "Married": false
  }, {
    "Name": "Wallace Christian",
    "Age": 43,
    "Country": 3,
    "Address": "416-8816 Mauris Avenue",
    "Married": true
  }, {
    "Name": "Caryn Maldonado",
    "Age": 40,
    "Country": 1,
    "Address": "108-282 Nonummy Ave",
    "Married": false
  }, {
    "Name": "Whilemina Frank",
    "Age": 20,
    "Country": 7,
    "Address": "P.O. Box 681, 3938 Egestas. Av.",
    "Married": true
  }, {
    "Name": "Emery Moon",
    "Age": 41,
    "Country": 4,
    "Address": "Ap #717-8556 Non Road",
    "Married": true
  }, {
    "Name": "Price Watkins",
    "Age": 35,
    "Country": 4,
    "Address": "832-7810 Nunc Rd.",
    "Married": false
  }, {
    "Name": "Lydia Castillo",
    "Age": 59,
    "Country": 7,
    "Address": "5280 Placerat, Ave",
    "Married": true
  }, {
    "Name": "Lawrence Conway",
    "Age": 53,
    "Country": 1,
    "Address": "Ap #452-2808 Imperdiet St.",
    "Married": false
  }, {
    "Name": "Kalia Nicholson",
    "Age": 67,
    "Country": 5,
    "Address": "P.O. Box 871, 3023 Tellus Road",
    "Married": true
  }, {
    "Name": "Brielle Baxter",
    "Age": 45,
    "Country": 3,
    "Address": "Ap #822-9526 Ut, Road",
    "Married": true
  }, {
    "Name": "Valentine Brady",
    "Age": 72,
    "Country": 7,
    "Address": "8014 Enim. Road",
    "Married": true
  }, {
    "Name": "Rebecca Gardner",
    "Age": 57,
    "Country": 4,
    "Address": "8655 Arcu. Road",
    "Married": true
  }, {
    "Name": "Vladimir Tate",
    "Age": 26,
    "Country": 1,
    "Address": "130-1291 Non, Rd.",
    "Married": true
  }, {
    "Name": "Vernon Hays",
    "Age": 56,
    "Country": 4,
    "Address": "964-5552 In Rd.",
    "Married": true
  }, {
    "Name": "Allegra Hull",
    "Age": 22,
    "Country": 4,
    "Address": "245-8891 Donec St.",
    "Married": true
  }, {
    "Name": "Hu Hendrix",
    "Age": 65,
    "Country": 7,
    "Address": "428-5404 Tempus Ave",
    "Married": true
  }, {
    "Name": "Kenyon Battle",
    "Age": 32,
    "Country": 2,
    "Address": "921-6804 Lectus St.",
    "Married": false
  }, {
    "Name": "Gloria Nielsen",
    "Age": 24,
    "Country": 4,
    "Address": "Ap #275-4345 Lorem, Street",
    "Married": true
  }, {
    "Name": "Illiana Kidd",
    "Age": 59,
    "Country": 2,
    "Address": "7618 Lacus. Av.",
    "Married": false
  }, {
    "Name": "Adria Todd",
    "Age": 68,
    "Country": 6,
    "Address": "1889 Tincidunt Road",
    "Married": false
  }, {
    "Name": "Kirsten Mayo",
    "Age": 71,
    "Country": 1,
    "Address": "100-8640 Orci, Avenue",
    "Married": false
  }, {
    "Name": "Willa Hobbs",
    "Age": 60,
    "Country": 6,
    "Address": "P.O. Box 323, 158 Tristique St.",
    "Married": false
  }, {
    "Name": "Alexis Clements",
    "Age": 69,
    "Country": 5,
    "Address": "P.O. Box 176, 5107 Proin Rd.",
    "Married": false
  }, {
    "Name": "Akeem Conrad",
    "Age": 60,
    "Country": 2,
    "Address": "282-495 Sed Ave",
    "Married": true
  }, {
    "Name": "Montana Silva",
    "Age": 79,
    "Country": 6,
    "Address": "P.O. Box 120, 9766 Consectetuer St.",
    "Married": false
  }, {
    "Name": "Kaseem Hensley",
    "Age": 77,
    "Country": 6,
    "Address": "Ap #510-8903 Mauris. Av.",
    "Married": true
  }, {
    "Name": "Christopher Morton",
    "Age": 35,
    "Country": 5,
    "Address": "P.O. Box 234, 3651 Sodales Avenue",
    "Married": false
  }, {
    "Name": "Wade Fernandez",
    "Age": 49,
    "Country": 6,
    "Address": "740-5059 Dolor. Road",
    "Married": true
  }, {
    "Name": "Illiana Kirby",
    "Age": 31,
    "Country": 2,
    "Address": "527-3553 Mi Ave",
    "Married": false
  }, {
    "Name": "Kimberley Hurley",
    "Age": 65,
    "Country": 5,
    "Address": "P.O. Box 637, 9915 Dictum St.",
    "Married": false
  }, {
    "Name": "Arthur Olsen",
    "Age": 74,
    "Country": 5,
    "Address": "887-5080 Eget St.",
    "Married": false
  }, {
    "Name": "Brody Potts",
    "Age": 59,
    "Country": 2,
    "Address": "Ap #577-7690 Sem Road",
    "Married": false
  }, {
    "Name": "Dillon Ford",
    "Age": 60,
    "Country": 1,
    "Address": "Ap #885-9289 A, Av.",
    "Married": true
  }, {
    "Name": "Hannah Juarez",
    "Age": 61,
    "Country": 2,
    "Address": "4744 Sapien, Rd.",
    "Married": true
  }, {
    "Name": "Vincent Shaffer",
    "Age": 25,
    "Country": 2,
    "Address": "9203 Nunc St.",
    "Married": true
  }, {
    "Name": "George Holt",
    "Age": 27,
    "Country": 6,
    "Address": "4162 Cras Rd.",
    "Married": false
  }, {
    "Name": "Tobias Bartlett",
    "Age": 74,
    "Country": 4,
    "Address": "792-6145 Mauris St.",
    "Married": true
  }, {
    "Name": "Xavier Hooper",
    "Age": 35,
    "Country": 1,
    "Address": "879-5026 Interdum. Rd.",
    "Married": false
  }, {
    "Name": "Declan Dorsey",
    "Age": 31,
    "Country": 2,
    "Address": "Ap #926-4171 Aenean Road",
    "Married": true
  }, {
    "Name": "Clementine Tran",
    "Age": 43,
    "Country": 4,
    "Address": "P.O. Box 176, 9865 Eu Rd.",
    "Married": true
  }, {
    "Name": "Pamela Moody",
    "Age": 55,
    "Country": 6,
    "Address": "622-6233 Luctus Rd.",
    "Married": true
  }, {
    "Name": "Julie Leon",
    "Age": 43,
    "Country": 6,
    "Address": "Ap #915-6782 Sem Av.",
    "Married": true
  }, {
    "Name": "Shana Nolan",
    "Age": 79,
    "Country": 5,
    "Address": "P.O. Box 603, 899 Eu St.",
    "Married": false
  }, {
    "Name": "Vaughan Moody",
    "Age": 37,
    "Country": 5,
    "Address": "880 Erat Rd.",
    "Married": false
  }, {
    "Name": "Randall Reeves",
    "Age": 44,
    "Country": 3,
    "Address": "1819 Non Street",
    "Married": false
  }, {
    "Name": "Dominic Raymond",
    "Age": 68,
    "Country": 1,
    "Address": "Ap #689-4874 Nisi Rd.",
    "Married": true
  }, {
    "Name": "Lev Pugh",
    "Age": 69,
    "Country": 5,
    "Address": "Ap #433-6844 Auctor Avenue",
    "Married": true
  }, {
    "Name": "Desiree Hughes",
    "Age": 80,
    "Country": 4,
    "Address": "605-6645 Fermentum Avenue",
    "Married": true
  }, {
    "Name": "Idona Oneill",
    "Age": 23,
    "Country": 7,
    "Address": "751-8148 Aliquam Avenue",
    "Married": true
  }, {
    "Name": "Lani Mayo",
    "Age": 76,
    "Country": 1,
    "Address": "635-2704 Tristique St.",
    "Married": true
  }, {
    "Name": "Cathleen Bonner",
    "Age": 40,
    "Country": 1,
    "Address": "916-2910 Dolor Av.",
    "Married": false
  }, {
    "Name": "Sydney Murray",
    "Age": 44,
    "Country": 5,
    "Address": "835-2330 Fringilla St.",
    "Married": false
  }, {
    "Name": "Brenna Rodriguez",
    "Age": 77,
    "Country": 6,
    "Address": "3687 Imperdiet Av.",
    "Married": true
  }, {
    "Name": "Alfreda Mcdaniel",
    "Age": 38,
    "Country": 7,
    "Address": "745-8221 Aliquet Rd.",
    "Married": true
  }, {
    "Name": "Zachery Atkins",
    "Age": 30,
    "Country": 1,
    "Address": "549-2208 Auctor. Road",
    "Married": true
  }, {
    "Name": "Amelia Rich",
    "Age": 56,
    "Country": 4,
    "Address": "P.O. Box 734, 4717 Nunc Rd.",
    "Married": false
  }, {
    "Name": "Kiayada Witt",
    "Age": 62,
    "Country": 3,
    "Address": "Ap #735-3421 Malesuada Avenue",
    "Married": false
  }, {
    "Name": "Lysandra Pierce",
    "Age": 36,
    "Country": 1,
    "Address": "Ap #146-2835 Curabitur St.",
    "Married": true
  }, {
    "Name": "Cara Rios",
    "Age": 58,
    "Country": 4,
    "Address": "Ap #562-7811 Quam. Ave",
    "Married": true
  }, {
    "Name": "Austin Andrews",
    "Age": 55,
    "Country": 7,
    "Address": "P.O. Box 274, 5505 Sociis Rd.",
    "Married": false
  }, {
    "Name": "Lillian Peterson",
    "Age": 39,
    "Country": 2,
    "Address": "6212 A Avenue",
    "Married": false
  }, {
    "Name": "Adria Beach",
    "Age": 29,
    "Country": 2,
    "Address": "P.O. Box 183, 2717 Nunc Avenue",
    "Married": true
  }, {
    "Name": "Oleg Durham",
    "Age": 80,
    "Country": 4,
    "Address": "931-3208 Nunc Rd.",
    "Married": false
  }, {
    "Name": "Casey Reese",
    "Age": 60,
    "Country": 4,
    "Address": "383-3675 Ultrices, St.",
    "Married": false
  }, {
    "Name": "Kane Burnett",
    "Age": 80,
    "Country": 1,
    "Address": "759-8212 Dolor. Ave",
    "Married": false
  }, {
    "Name": "Stewart Wilson",
    "Age": 46,
    "Country": 7,
    "Address": "718-7845 Sagittis. Av.",
    "Married": false
  }, {
    "Name": "Charity Holcomb",
    "Age": 31,
    "Country": 6,
    "Address": "641-7892 Enim. Ave",
    "Married": false
  }, {
    "Name": "Kyra Cummings",
    "Age": 43,
    "Country": 4,
    "Address": "P.O. Box 702, 6621 Mus. Av.",
    "Married": false
  }, {
    "Name": "Stuart Wallace",
    "Age": 25,
    "Country": 7,
    "Address": "648-4990 Sed Rd.",
    "Married": true
  }, {
    "Name": "Carter Clarke",
    "Age": 59,
    "Country": 6,
    "Address": "Ap #547-2921 A Street",
    "Married": false
  }];

  db.users = [{
    "ID": "x",
    "Account": "A758A693-0302-03D1-AE53-EEFE22855556",
    "Name": "Carson Kelley",
    "RegisterDate": "2002-04-20T22:55:52-07:00"
  }, {
    "Account": "D89FF524-1233-0CE7-C9E1-56EFF017A321",
    "Name": "Prescott Griffin",
    "RegisterDate": "2011-02-22T05:59:55-08:00"
  }, {
    "Account": "06FAAD9A-5114-08F6-D60C-961B2528B4F0",
    "Name": "Amir Saunders",
    "RegisterDate": "2014-08-13T09:17:49-07:00"
  }, {
    "Account": "EED7653D-7DD9-A722-64A8-36A55ECDBE77",
    "Name": "Derek Thornton",
    "RegisterDate": "2012-02-27T01:31:07-08:00"
  }, {
    "Account": "2A2E6D40-FEBD-C643-A751-9AB4CAF1E2F6",
    "Name": "Fletcher Romero",
    "RegisterDate": "2010-06-25T15:49:54-07:00"
  }, {
    "Account": "3978F8FA-DFF0-DA0E-0A5D-EB9D281A3286",
    "Name": "Thaddeus Stein",
    "RegisterDate": "2013-11-10T07:29:41-08:00"
  }, {
    "Account": "658DBF5A-176E-569A-9273-74FB5F69FA42",
    "Name": "Nash Knapp",
    "RegisterDate": "2005-06-24T09:11:19-07:00"
  }, {
    "Account": "76D2EE4B-7A73-1212-F6F2-957EF8C1F907",
    "Name": "Quamar Vega",
    "RegisterDate": "2011-04-13T20:06:29-07:00"
  }, {
    "Account": "00E46809-A595-CE82-C5B4-D1CAEB7E3E58",
    "Name": "Philip Galloway",
    "RegisterDate": "2008-08-21T18:59:38-07:00"
  }, {
    "Account": "C196781C-DDCC-AF83-DDC2-CA3E851A47A0",
    "Name": "Mason French",
    "RegisterDate": "2000-11-15T00:38:37-08:00"
  }, {
    "Account": "5911F201-818A-B393-5888-13157CE0D63F",
    "Name": "Ross Cortez",
    "RegisterDate": "2010-05-27T17:35:32-07:00"
  }, {
    "Account": "B8BB78F9-E1A1-A956-086F-E12B6FE168B6",
    "Name": "Logan King",
    "RegisterDate": "2003-07-08T16:58:06-07:00"
  }, {
    "Account": "06F636C3-9599-1A2D-5FD5-86B24ADDE626",
    "Name": "Cedric Leblanc",
    "RegisterDate": "2011-06-30T14:30:10-07:00"
  }, {
    "Account": "FE880CDD-F6E7-75CB-743C-64C6DE192412",
    "Name": "Simon Sullivan",
    "RegisterDate": "2013-06-11T16:35:07-07:00"
  }, {
    "Account": "BBEDD673-E2C1-4872-A5D3-C4EBD4BE0A12",
    "Name": "Jamal West",
    "RegisterDate": "2001-03-16T20:18:29-08:00"
  }, {
    "Account": "19BC22FA-C52E-0CC6-9552-10365C755FAC",
    "Name": "Hector Morales",
    "RegisterDate": "2012-11-01T01:56:34-07:00"
  }, {
    "Account": "A8292214-2C13-5989-3419-6B83DD637D6C",
    "Name": "Herrod Hart",
    "RegisterDate": "2008-03-13T19:21:04-07:00"
  }, {
    "Account": "0285564B-F447-0E7F-EAA1-7FB8F9C453C8",
    "Name": "Clark Maxwell",
    "RegisterDate": "2004-08-05T08:22:24-07:00"
  }, {
    "Account": "EA78F076-4F6E-4228-268C-1F51272498AE",
    "Name": "Reuben Walter",
    "RegisterDate": "2011-01-23T01:55:59-08:00"
  }, {
    "Account": "6A88C194-EA21-426F-4FE2-F2AE33F51793",
    "Name": "Ira Ingram",
    "RegisterDate": "2008-08-15T05:57:46-07:00"
  }, {
    "Account": "4275E873-439C-AD26-56B3-8715E336508E",
    "Name": "Damian Morrow",
    "RegisterDate": "2016-09-13T01:50:55-07:00"
  }, {
    "Account": "A0D733C4-9070-B8D6-4387-D44F0BA515BE",
    "Name": "Macon Farrell",
    "RegisterDate": "2011-03-14T05:41:40-07:00"
  }, {
    "Account": "B3683DE8-C2FA-7CA0-A8A6-8FA7E954F90A",
    "Name": "Joel Galloway",
    "RegisterDate": "2003-02-03T04:19:01-08:00"
  }, {
    "Account": "01D95A8E-91BC-2050-F5D0-4437AAFFD11F",
    "Name": "Rigel Horton",
    "RegisterDate": "2016-06-20T11:53:11-07:00"
  }, {
    "Account": "F0D12CC0-31AC-A82E-FD73-EEEFDBD21A36",
    "Name": "Sylvester Gaines",
    "RegisterDate": "2004-03-12T09:57:13-08:00"
  }, {
    "Account": "874FCC49-9A61-71BC-2F4E-2CE88348AD7B",
    "Name": "Abbot Mckay",
    "RegisterDate": "2008-12-26T20:42:57-08:00"
  }, {
    "Account": "B8DA1912-20A0-FB6E-0031-5F88FD63EF90",
    "Name": "Solomon Green",
    "RegisterDate": "2013-09-04T01:44:47-07:00"
  }];

}());

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  jsGrid.setDefaults({
    tableClass: "jsgrid-table table table-striped table-hover"
  });

  jsGrid.setDefaults("text", {
    _createTextBox: function() {
      return $("<input>").attr("type", "text").attr("class", "form-control input-sm");
    }
  });

  jsGrid.setDefaults("number", {
    _createTextBox: function() {
      return $("<input>").attr("type", "number").attr("class", "form-control input-sm");
    }
  });

  jsGrid.setDefaults("textarea", {
    _createTextBox: function() {
      return $("<input>").attr("type", "textarea").attr("class", "form-control");
    }
  });

  jsGrid.setDefaults("control", {
    _createGridButton: function(cls, tooltip, clickHandler) {
      var grid = this._grid;

      return $("<button>").addClass(this.buttonClass)
        .addClass(cls)
        .attr({
          type: "button",
          title: tooltip
        })
        .on("click", function(e) {
          clickHandler(grid, e);
        });
    }
  });

  jsGrid.setDefaults("select", {
    _createSelect: function() {
      var $result = $("<select>").attr("class", "form-control input-sm"),
        valueField = this.valueField,
        textField = this.textField,
        selectedIndex = this.selectedIndex;

      $.each(this.items, function(index, item) {
        var value = valueField ? item[valueField] : index,
          text = textField ? item[textField] : item;

        var $option = $("<option>")
          .attr("value", value)
          .text(text)
          .appendTo($result);

        $option.prop("selected", (selectedIndex === index));
      });

      return $result;
    }
  });

  // Example Basic
  // -------------------
  (function() {
    $('#exampleBasic').jsGrid({
      height: "500px",
      width: "100%",

      filtering: true,
      editing: true,
      sorting: true,
      paging: true,
      autoload: true,

      pageSize: 15,
      pageButtonCount: 5,

      deleteConfirm: "Do you really want to delete the client?",

      controller: db,

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 70
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married",
        sorting: false
      }, {
        type: "control"
      }]
    });
  })();


  // Example Static Data
  // ----------------------------
  (function() {
    $('#exampleStaticData').jsGrid({
      height: "500px",
      width: "100%",

      sorting: true,
      paging: true,

      data: db.clients,

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 50
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married"
      }]
    });
  })();

  // Example OData Service
  // -------------------
  (function() {
    $('#exampleOData').jsGrid({
      height: "500px",
      width: "100%",

      sorting: true,
      paging: false,
      autoload: true,

      controller: {
        loadData: function() {
          var d = $.Deferred();

          $.ajax({
            url: "http://services.odata.org/V3/(S(3mnweai3qldmghnzfshavfok))/OData/OData.svc/Products",
            dataType: "json"
          }).done(function(response) {
            d.resolve(response.value);
          });

          return d.promise();
        }
      },

      fields: [{
        name: "Name",
        type: "text"
      }, {
        name: "Description",
        type: "textarea",
        width: 150
      }, {
        name: "Rating",
        type: "number",
        width: 50,
        align: "center",
        itemTemplate: function(value) {
          return $("<div>").addClass("rating text-nowrap").append(Array(value + 1).join('<i class="icon md-star orange-600 margin-right-3"></i>'));
        }
      }, {
        name: "Price",
        type: "number",
        width: 50,
        itemTemplate: function(value) {
          return value.toFixed(2) + "$";
        }
      }]
    });
  })();

  // Example Sorting
  // ---------------
  (function() {
    $('#exampleSorting').jsGrid({
      height: "500px",
      width: "100%",

      autoload: true,
      selecting: false,

      controller: db,

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 50
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married"
      }]
    });

    $("#sortingField").on('change', function() {
      var field = $(this).val();
      $("#exampleSorting").jsGrid("sort", field);
    });
  })();

  // Example Loading Data by Page
  // ----------------------------
  (function() {
    $('#exampleLoadingByPage').jsGrid({
      height: "500px",
      width: "100%",

      autoload: true,
      paging: true,
      pageLoading: true,
      pageSize: 15,
      pageIndex: 2,

      controller: {
        loadData: function(filter) {
          var startIndex = (filter.pageIndex - 1) * filter.pageSize;
          return {
            data: db.clients.slice(startIndex, startIndex + filter.pageSize),
            itemsCount: db.clients.length
          };
        }
      },

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 50
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married"
      }]
    });


    $("#pager").on("change", function() {
      var page = parseInt($(this).val(), 10);
      $("#exampleLoadingByPage").jsGrid("openPage", page);
    });
  })();

  // Example Custom View
  // -------------------
  (function() {
    $('#exampleCustomView').jsGrid({
      height: "500px",
      width: "100%",

      filtering: true,
      editing: true,
      sorting: true,
      paging: true,
      autoload: true,

      pageSize: 15,
      pageButtonCount: 5,

      controller: db,

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 70
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married",
        sorting: false
      }, {
        type: "control",
        modeSwitchButton: false,
        editButton: false
      }]
    });

    $(".views").on("change", function() {
      var $cb = $(this);
      $("#exampleCustomView").jsGrid("option", $cb.attr("value"), $cb.is(":checked"));
    });
  })();


  // Example Custom Row Renderer
  // ---------------------------
  (function() {
    $('#exampleCustomRowRenderer').jsGrid({
      height: "500px",
      width: "100%",

      autoload: true,
      paging: true,

      controller: {
        loadData: function() {
          var deferred = $.Deferred();

          $.ajax({
            url: 'http://api.randomuser.me/?results=40',
            dataType: 'json',
            success: function(data) {
              deferred.resolve(data.results);
            }
          });

          return deferred.promise();
        }
      },

      rowRenderer: function(item) {
        var user = item.user;
        var $photo = $("<div>").addClass("media-left").append(
          $('<a>').addClass('avatar avatar-lg').attr('href', 'javascript:void(0)').append(
            $("<img>").attr("src", user.picture.medium)
          )
        );
        var $info = $("<div>").addClass("media-body")
          .append($("<p>").append($("<strong>").text(user.name.first.capitalize() + " " + user.name.last.capitalize())))
          .append($("<p>").text("Location: " + user.location.city.capitalize() + ", " + user.location.street))
          .append($("<p>").text("Email: " + user.email))
          .append($("<p>").text("Phone: " + user.phone))
          .append($("<p>").text("Cell: " + user.cell));

        return $("<tr>").append(
          $('<td>').append(
            $('<div class="media">').append($photo).append($info)
          )
        );
      },

      fields: [{
        title: "Clients"
      }]
    });

    String.prototype.capitalize = function() {
      return this.charAt(0).toUpperCase() + this.slice(1);
    };
  })();

  // Example Batch Delete
  // --------------------
  (function() {
    $('#exampleBatchDelete').jsGrid({
      height: "500px",
      width: "100%",

      autoload: true,
      confirmDeleting: false,
      paging: true,
      controller: {
        loadData: function() {
          return db.clients;
        }
      },
      fields: [{
        headerTemplate: function() {
          return $("<button>").attr("type", "button").attr("class", "btn btn-primary btn-xs").text("Delete")
            .on("click", function() {
              deleteSelectedItems();
            });
        },
        itemTemplate: function(_, item) {
          return $("<input>").attr("type", "checkbox")
            .on("change", function() {
              $(this).is(":checked") ? selectItem(item) : unselectItem(item);
            });
        },
        align: "center",
        width: 50
      }, {
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 50
      }, {
        name: "Address",
        type: "text",
        width: 200
      }]
    });

    var selectedItems = [];

    var selectItem = function(item) {
      selectedItems.push(item);
    };

    var unselectItem = function(item) {
      selectedItems = $.grep(selectedItems, function(i) {
        return i !== item;
      });
    };

    var deleteSelectedItems = function() {
      if (!selectedItems.length || !confirm("Are you sure?"))
        return;

      var $grid = $("#exampleBatchDelete");

      $.each(selectedItems, function(_, item) {
        $grid.jsGrid("deleteItem", item);
      });

      selectedItems = [];
    };
  })();

  // Example Rows Reordering
  // -----------------------
  (function() {
    $('#exampleRowsReordering').jsGrid({
      height: "500px",
      width: "100%",

      autoload: true,

      rowClass: function(item, itemIndex) {
        return "client-" + itemIndex;
      },

      controller: {
        loadData: function() {
          return db.clients.slice(0, 15);
        }
      },

      fields: [{
        name: "Name",
        type: "text",
        width: 150
      }, {
        name: "Age",
        type: "number",
        width: 50
      }, {
        name: "Address",
        type: "text",
        width: 200
      }, {
        name: "Country",
        type: "select",
        items: db.countries,
        valueField: "Id",
        textField: "Name"
      }, {
        name: "Married",
        type: "checkbox",
        title: "Is Married",
        sorting: false
      }]
    });

    var $gridData = $("#exampleRowsReordering .jsgrid-grid-body tbody");

    $gridData.sortable({
      update: function(e, ui) {
        // array of indexes
        var clientIndexRegExp = /\s+client-(\d+)\s+/;
        var indexes = $.map($gridData.sortable("toArray", {
          attribute: "class"
        }), function(classes) {
          return clientIndexRegExp.exec(classes)[1];
        });
        alert("Reordered indexes: " + indexes.join(", "));

        // arrays of items
        var items = $.map($gridData.find("tr"), function(row) {
          return $(row).data("JSGridItem");
        });
        console && console.log("Reordered items", items);
      }
    });
  })();


  // Example Custom Grid Field
  // -------------------------
  (function() {
    var MyDateField = function(config) {
      jsGrid.Field.call(this, config);
    };

    MyDateField.prototype = new jsGrid.Field({
      sorter: function(date1, date2) {
        return new Date(date1) - new Date(date2);
      },

      itemTemplate: function(value) {
        return new Date(value).toDateString();
      },

      insertTemplate: function() {
        if (!this.inserting)
          return "";

        var $result = this.insertControl = this._createTextBox();
        return $result;
      },

      editTemplate: function(value) {
        if (!this.editing)
          return this.itemTemplate(value);

        var $result = this.editControl = this._createTextBox();
        $result.val(value);
        return $result;
      },


      insertValue: function() {
        return this.insertControl.datepicker("getDate");
      },

      editValue: function() {
        return this.editControl.datepicker("getDate");
      },

      _createTextBox: function() {
        return $("<input>").attr("type", "text").addClass('form-control input-sm').datepicker({
          autoclose: true
        });
      }
    });

    jsGrid.fields.myDateField = MyDateField;

    $("#exampleCustomGridField").jsGrid({
      height: "500px",
      width: "100%",

      inserting: true,
      editing: true,
      sorting: true,
      paging: true,

      data: db.users,

      fields: [{
        name: "Account",
        width: 150,
        align: "center"
      }, {
        name: "Name",
        type: "text"
      }, {
        name: "RegisterDate",
        type: "myDateField",
        width: 100,
        align: "center"
      }, {
        type: "control",
        editButton: false,
        modeSwitchButton: false
      }]
    });

  })();


})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    Waves.attach('.page-content .btn-flat');
    Waves.attach('.page-content .btn-round', ['waves-round', 'waves-light']);
    Waves.attach('.page-content .btn-pure', ['waves-circle', 'waves-classic']);
    Waves.attach('.page-content .btn-floating', ['waves-float', 'waves-light']);
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    // Example Slick Single Item
    // -------------------------
    $('#exampleSingleItem').slick();


    // Example Slick Multiple Items
    // ----------------------------
    $('#exampleMultipleItems').slick({
      infinite: true,
      slidesToShow: 3,
      slidesToScroll: 3
    });

    // Example Slick Responsive Display
    // --------------------------------
    $('#exampleResponsive').slick({
      dots: true,
      infinite: false,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll: 4,
      responsive: [{
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 3,
            infinite: true,
            dots: true
          }
        }, {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2
          }
        }, {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
      ]
    });

    // Example Slick Variable Width
    // ----------------------------
    $('#exampleVariableWidth').slick({
      dots: true,
      infinite: true,
      speed: 300,
      slidesToShow: 1,
      centerMode: true,
      variableWidth: true
    });

    // Example Slick Adaptive Height
    // -----------------------------
    $('#exampleAdaptiveHeight').slick({
      dots: true,
      infinite: true,
      speed: 300,
      slidesToShow: 1,
      adaptiveHeight: true
    });


    // Example Slick Data Attribute Settings
    // -----------------------------
    $('#exampleData').slick();


    // Example Slick Center Mode
    // -------------------------
    $('#exampleCenter').slick({
      centerMode: true,
      centerPadding: '60px',
      slidesToShow: 3,
      responsive: [{
        breakpoint: 768,
        settings: {
          arrows: false,
          centerMode: true,
          centerPadding: '40px',
          slidesToShow: 3
        }
      }, {
        breakpoint: 480,
        settings: {
          arrows: false,
          centerMode: true,
          centerPadding: '40px',
          slidesToShow: 1
        }
      }]
    });

    // Example Slick Lazy Loading
    // --------------------------

    $('#exampleLazy').slick({
      lazyLoad: 'ondemand',
      slidesToShow: 3,
      slidesToScroll: 1
    });


    // Example Slick Autoplay
    // ----------------------
    $('#exampleAutoplay').slick({
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 3,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 2000
    });

    // Example Slick Fade
    // ------------------
    $('#exampleFade').slick({
      dots: true,
      infinite: true,
      speed: 500,
      fade: true,
      slide: 'div',
      cssEase: 'linear'
    });


    // Example Slick Add & Remove
    // --------------------------
    var slideIndex = 1;
    $('#exampleAddRemove').slick({
      dots: true,
      slidesToShow: 3,
      speed: 500,
      slidesToScroll: 3
    });

    $('#exampleAddSlide').on('click', function() {
      slideIndex++;
      $('#exampleAddRemove').slick('slickAdd', '<div><h3>' + slideIndex + '</h3></div>');
    });

    $('#exampleRemoveSlide').on('click', function() {
      $('#exampleAddRemove').slick('slickRemove', slideIndex - 1);
      if (slideIndex !== 0) {
        slideIndex--;
      }
    });


    // Example Slick Filtering
    // -----------------------
    $('#exampleFiltering').slick({
      slidesToShow: 4,
      slidesToScroll: 4
    });

    var filtered = false;
    $('#exampleFilter').on('click', function() {
      if (filtered === false) {
        $('#exampleFiltering').slick('slickFilter', ':even');
        $(this).text('Unfilter Slides');
        filtered = true;
      } else {
        $('#exampleFiltering').slick('slickUnfilter');
        $(this).text('Filter Slides');
        filtered = false;
      }
    });

  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    Waves.attach('.dropdown-menu:not([class*="dropdown-menu-"]) li a', ['waves-classic']);
    Waves.attach('[class*="dropdown-menu-"]:not(.dropdown-menu-right):not(.dropdown-menu-left) li a', ['waves-light']);
    Waves.attach('.dropdown-menu-right li a', ['waves-classic']);
    Waves.attach('.dropdown-menu-left li a', ['waves-classic']);
  });

  $(".example-dropdown-js select").dropdown();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.extend({
      run: function() {
        $('#icon_change').asRange({
          tip: false,
          scale: false,
          onChange: function(value) {
            $('#icon_size').text(value + "px");
            $('.panel .icon').css('font-size', value);
          }
        });
        $('.input-search input[type=text]').on('keyup', function() {
          var val = $(this).val();
          console.log(val);
          if (val !== '') {
            $('[data-name]').addClass('is-hide');
            $('[data-name*=' + val + ']').removeClass('is-hide');
          } else {
            $('[data-name]').removeClass('is-hide');
          }

          $('.icon-group').each(function() {
            var $group = $(this);
            if ($group.find('[data-name]:not(.is-hide)').length === 0) {
              $group.hide();
            } else {
              $group.show();
            }
          });

        });
      }
    }).run();

  });
})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    Waves.attach('.list-group > a:not(.disabled)', ['waves-block', 'waves-classic']);
  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();

    var $panel = $('#examplePanel');
    var api = $panel.data('panel-api');

    // Fullscreen
    $('#exampleTogglFullscreene').on('click', function() {
      api.toggleFullscreen();
    });

    $('#exampleEnterFullscreen').on('click', function() {
      api.enterFullscreen();
    });

    $('#exampleLeaveFullscreen').on('click', function() {
      api.leaveFullscreen();
    });

    // Content
    $('#exampleToggleContent').on('click', function() {
      api.toggleContent();
    });

    $('#exampleShowContent').on('click', function() {
      api.showContent();
    });

    $('#exampleHideContent').on('click', function() {
      api.hideContent();
    });

    // Open / Close
    $('#exampleToggle').on('click', function() {
      api.toggle();
    });

    $('#exampleOpen').on('click', function() {
      api.open();
    });

    $('#exampleClose').on('click', function() {
      api.close();
    });


    // Refresh
    var even = false;
    $('#exampleReplace').on('click', function() {
      api.load(function(done) {
        var $panel = $(this);
        var content;

        if (even) {
          content = 'Lorem ipsum Adipisicing qui pariatur elit veniam reprehenderit dolore mollit amet deserunt et veniam cupidatat deserunt cupidatat dolore pariatur ullamco dolor adipisicing officia sed mollit consequat veniam culpa fugiat commodo exercitation quis veniam cupidatat eu aliquip elit dolore commodo deserunt fugiat esse in ut Excepteur non sint consequat Ut id fugiat magna ex adipisicing consequat cillum enim ad sint officia enim adipisicing aute aute ea pariatur quis dolor esse sed do veniam cupidatat magna proident in consectetur sit eiusmod sint incididunt qui sed qui deserunt consequat nulla ea esse enim minim amet eu anim labore Excepteur est ut sit commodo sit aute veniam in in quis amet ea dolore proident incididunt pariatur laboris mollit veniam est amet reprehenderit sint do id amet cillum reprehenderit irure minim culpa Duis in officia mollit veniam Excepteur officia incididunt Ut non incididunt amet ut mollit adipisicing laboris dolor Excepteur adipisicing ut sint Duis laborum culpa est Excepteur eiusmod deserunt labore nisi ad laboris minim fugiat ullamco anim enim esse eu tempor non adipisicing dolor dolor labore fugiat officia et occaecat consectetur dolor cupidatat consectetur est quis enim esse in occaecat cillum proident laborum ad reprehenderit Excepteur pariatur velit magna et reprehenderit incididunt dolore Duis occaecat ad Duis eiusmod in ullamco adipisicing est incididunt labore amet adipisicing ad Excepteur officia consectetur voluptate nulla occaecat qui sed cillum aliqua sit tempor ea officia est reprehenderit irure cupidatat.';
          even = false;
        } else {
          content = 'Lorem ipsum Laborum aute qui Ut commodo enim sunt culpa tempor cupidatat non ut proident Duis sunt pariatur id adipisicing sint sunt dolore ullamco Excepteur aute veniam nostrud reprehenderit Excepteur cupidatat aute sunt pariatur labore aute nostrud veniam Ut Ut reprehenderit incididunt ex ut do est consectetur est sint dolore id non ad esse eu enim qui deserunt dolor laboris velit cupidatat Duis tempor sed et culpa in do ea minim velit adipisicing ullamco sit qui consectetur nisi qui nisi labore sunt incididunt anim consequat consectetur commodo aliqua officia et proident deserunt culpa nulla culpa exercitation Duis elit cillum in id laboris minim est aute in esse voluptate dolor eu velit Excepteur sint dolore incididunt exercitation enim eiusmod officia quis aliqua reprehenderit irure quis non amet ullamco laboris dolor in in consectetur cupidatat est ea do nisi ut nulla in Duis irure irure minim sed officia mollit irure reprehenderit proident ullamco sed pariatur dolore dolor sunt dolor aute magna in tempor nisi ullamco eiusmod ut non fugiat dolor in.';
          even = true;
        }

        $panel.find('.panel-body').html(content);
        done();
      });
    });

    $('#exampleLoad').on('click', function() {
      api.load();
    });

    $('#exampleDone').on('click', function() {
      api.done();
    });
  });

  window.customRefreshCallback = function(done) {
    var $panel = $(this);
    setTimeout(function() {
      done();
      $panel.find('.panel-body').html('Lorem ipsum Ad reprehenderit pariatur qui labore nulla elit non velit non consectetur dolore veniam qui ullamco incididunt laboris quis incididunt nisi culpa incididunt sit est occaecat pariatur nulla aliqua amet est voluptate.');
    }, 1000);
  };

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Button Random
  // ---------------------
  (function() {
    $('#exampleButtonRandom').on('click', function(e) {
      e.preventDefault();

      $('[data-plugin="progress"]').each(function() {
        var number = Math.round(Math.random(1) * 100) + '%';
        $(this).asProgress('go', number);
      });
    });
  })();


  // Example Panel With Tool
  // -----------------------
  window.customRefreshCallback = function(done) {
    var $panel = $(this);
    setTimeout(function() {
      done();
      $panel.find('.panel-body').html('Lorem ipsum In nostrud Excepteur velit reprehenderit quis consequat veniam officia nisi labore in est.');
    }, 1000);
  };

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });

  // Example Progress Animation
  // --------------------------
  (function() {
    $('#exampleButtonStart').on('click', function() {
      $('[data-plugin="progress"]').asProgress('start');
    });
    $('#exampleButtonFinish').on('click', function() {
      $('[data-plugin="progress"]').asProgress('finish');
    });
    $('#exampleButtonGoto').on('click', function() {
      $('[data-plugin="progress"]').asProgress('go', 50);
    });
    $('#exampleButtonGotoPercentage').on('click', function() {
      $('[data-plugin="progress"]').asProgress('go', '50%');
    });
    $('#exampleButtonStop').on('click', function() {
      $('[data-plugin="progress"]').asProgress('stop');
    });
    $('#exampleButtonReset').on('click', function() {
      $('[data-plugin="progress"]').asProgress('reset');
    });
    $('#exampleButtonRandom').on('click', function(e) {
      e.preventDefault();

      $('[data-plugin="progress"]').each(function() {
        var number = Math.round(Math.random(1) * 100) + '%';
        $(this).asProgress('go', number);
      });
    });
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';

  var Site = window.Site;

  $(document).ready(function($) {
    Site.run();
  });


  var defaults = $.components.getDefaults("webuiPopover");

  // Example Webui Popover Pop with Table
  // ------------------------------------
  (function() {
    var tableContent = $('#examplePopoverTable').html(),
      tableSettings = {
        title: 'WebUI Popover',
        content: tableContent,
        width: 500
      };

    $('#examplePopWithTable').webuiPopover($.extend({}, defaults, tableSettings));
  })();

  // Example Webui Popover Pop with List
  // -----------------------------------
  (function() {
    var listContent = $('#examplePopoverList').html(),
      listSettings = {
        content: listContent,
        title: '',
        padding: false
      };

    $('#examplePopWithList').webuiPopover($.extend({}, defaults, listSettings));

  })();

  // Example Webui Popover Pop with Large Content
  // --------------------------------------------
  (function() {
    var largeContent = $('#examplePopoverLargeContent').html(),
      largeSettings = {
        title: 'WebUI Popover',
        content: largeContent,
        width: 400,
        height: 350,
        closeable: true
      };

    $('#examplePopWithLargeContent').webuiPopover($.extend({}, defaults, largeSettings));
  })();

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  // widget chart
  $(document).ready(function(jQuery) {
    Site.run();

    //chart-three-linearea
    new Chartist.Line('#chartThreeLinearea .ct-chart', {
      labels: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
      series: [
        [4, 4.5, 4.3, 4, 5, 6, 5.5],
        [3, 2.5, 3, 3.5, 4.2, 4, 5],
        [1, 2, 2.5, 2, 3, 2.8, 4]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-line-pie
    new Chartist.Line("#chartLinePie .chart-line", {
      labels: ['1', '2', '3', '4', '5', '6', '7', '8'],
      series: [
        [4, 5, 3, 6, 7, 5.5, 5.8, 4.6]
      ]
    }, {
      low: 0,
      showArea: false,
      showPoint: true,
      showLine: true,
      fullWidth: true,
      lineSmooth: false,
      chartPadding: {
        top: 4,
        right: 4,
        bottom: -20,
        left: 4
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    new Chartist.Pie('#chartLinePie .chart-pie', {
      series: [35, 65]
    }, {
      donut: true,
      donutWidth: 10,
      startAngle: 0,
      showLabel: false
    });

    //chart-bar-pie
    new Chartist.Bar("#chartBarPie .chart-bar", {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'],
      series: [
        [50, 90, 100, 90, 110, 100, 120, 130, 115, 95, 80, 85]
      ]
    }, {
      low: 0,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    new Chartist.Pie('#chartBarPie .chart-pie', {
      series: [70, 30]
    }, {
      donut: true,
      donutWidth: 10,
      startAngle: 0,
      showLabel: false
    });


    //chart-bar-stacked
    var stacked_bar = new Chartist.Bar('#chartBarStacked .ct-chart', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M'],
      series: [
        [11, 19, 17, 13, 2, 11, 26, 20, 27, 5, 22, 4],
        [6, 18, 7, 9, 26, 24, 3, 18, 28, 21, 19, 12],
        [9, 10, 22, 14, 23, 19, 15, 25, 28, 21, 17, 17]
      ]
    }, {
      stackBars: true,
      fullWidth: true,
      seriesBarDistance: 0,
      chartPadding: {
        top: -10,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: true,
        showGrid: false,
        offset: 30
      },
      axisY: {
        showLabel: true,
        showGrid: true,
        offset: 30
      }
    });

    //chart-pie
    new Chartist.Pie('#chartPie .ct-chart', {
      series: [35, 20, 45]
    }, {
      donut: true,
      donutWidth: 10,
      startAngle: 0,
      showLabel: false
    });

    //chart-bar-simple
    new Chartist.Bar("#chartBarSimple .ct-chart", {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'],
      series: [
        [6, 3, 2, 5, 4, 7, 5, 9, 4, 5, 4, 9, 8, 3, 6, 4, 8, 6, 8, 6, 4]
      ]
    }, {
      low: 0,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-linearea-simple
    new Chartist.Line('#chartLineareaSimple .ct-chart', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'],
      series: [
        [1, 6, 4, 9, 1, 6, 4, 9, 8, 6, 5, 1, 4, 6, 4, 9, 1, 3, 1, 9, ],

      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: true,
      fullWidth: true,
      lineSmooth: false,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-linearea-withfooter
    new Chartist.Line('#chartLineareaWithfooter .ct-chart', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G'],
      series: [
        [1, 6, 4, 9, 1, 6, 4, 9]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: true,
      fullWidth: true,
      lineSmooth: false,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-bar-withfooter
    new Chartist.Bar('#chartBarWithfooter .ct-chart', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'],
      series: [
        [160, 200, 150, 400, 460, 440, 240, 250, 50, 200, 360, 150, 380, 240, 460],
        [600 - 160, 600 - 200, 600 - 150, 600 - 400, 600 - 460, 600 - 440, 600 - 240, 600 - 250, 600 - 50, 600 - 200, 600 - 360, 600 - 150, 600 - 380, 600 - 240, 600 - 460]
      ]
    }, {
      stackBars: true,
      fullWidth: true,
      seriesBarDistance: 0,
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });


    //chart-linebar-large
    new Chartist.Line("#chartLinebarLarge .chart-line", {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      series: [
        [20, 50, 70, 110, 100, 200, 230, 50, 80, 140, 130, 150]
      ]
    }, {
      low: 0,
      showArea: false,
      showPoint: false,
      showLine: true,
      lineSmooth: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 10,
        bottom: 0,
        left: 10
      },
      axisX: {
        showLabel: true,
        showGrid: false,
        offset: 30
      },
      axisY: {
        showLabel: true,
        showGrid: true,
        offset: 30
      }
    });

    new Chartist.Bar('#chartLinebarLarge .chart-bar', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'],
      series: [
        [6, 3, 2, 5, 4, 7, 5, 9, 4, 5, 4, 9, 8, 3, 6, 4, 8, 6, 8, 6, 4, 3, 6, 4],
      ]
    }, {
      stackBars: true,
      fullWidth: true,
      seriesBarDistance: 0,
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-line-time
    var line_time_labels = [];
    var line_time_data = [];
    var line_time_totalPoints = 100;
    var line_time_updateInterval = 1000;
    var line_time_now = new Date().getTime();

    function line_time_getData() {
      line_time_labels.shift();
      line_time_data.shift();

      while (line_time_data.length < line_time_totalPoints) {
        var x = Math.random() * 100;
        line_time_labels.push(line_time_now += line_time_updateInterval);
        line_time_data.push(x);
      }
    }

    var lineTime = {
      labels: line_time_labels,
      series: [
        line_time_data
      ]
    };

    var lineTimeOptions = {
      low: 0,
      showArea: false,
      showPoint: false,
      showLine: true,
      lineSmooth: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    };

    new Chartist.Line("#chartLineTime .chart-line", lineTime, lineTimeOptions);

    function line_time_update() {
      line_time_getData();

      new Chartist.Line("#chartLineTime .chart-line", lineTime, lineTimeOptions);
      setTimeout(line_time_update, line_time_updateInterval);
    }

    line_time_update();


    new Chartist.Pie('#chartLineTime .chart-pie-left', {
      series: [50, 50]
    }, {
      donut: true,
      donutWidth: 10,
      startAngle: 0,
      showLabel: false
    });

    new Chartist.Pie('#chartLineTime .chart-pie-right', {
      series: [80, 20]
    }, {
      donut: true,
      donutWidth: 10,
      startAngle: 0,
      showLabel: false
    });

    // chart-barline-mix
    var mix_data = {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'],
      series: [
        [50, 90, 100, 90, 110, 100, 120, 130, 115, 95, 80, 85, 60, 100, 90]
      ]
    };

    new Chartist.Bar("#chartBarlineMix .chart-bar", mix_data, {
      low: 0,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: -7,
        bottom: 0,
        left: -7
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    new Chartist.Line("#chartBarlineMix .chart-line", mix_data, {
      low: 0,
      showArea: false,
      showPoint: false,
      showLine: true,
      lineSmooth: false,
      fullWidth: true,
      chartPadding: {
        top: 50,
        right: 4,
        bottom: 0,
        left: 4
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-barline-mix-two
    new Chartist.Bar("#chartBarlineMixTwo .small-bar-one", {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
      series: [
        [50, 90, 100, 90, 110, 100, 120, 130]
      ]
    }, {
      low: 0,
      fullWidth: true,
      chartPadding: {
        top: -10,
        right: 0,
        bottom: 0,
        left: 20
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    new Chartist.Bar("#chartBarlineMixTwo .small-bar-two", {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
      series: [
        [50, 90, 100, 90, 110, 100, 120, 120]
      ]
    }, {
      low: 0,
      fullWidth: true,
      chartPadding: {
        top: -10,
        right: 0,
        bottom: 0,
        left: 20
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    new Chartist.Line("#chartBarlineMixTwo .line-chart", {
      labels: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
      series: [
        [20, 50, 70, 110, 100, 200, 230],
        [50, 80, 140, 130, 150, 110, 160]
      ]
    }, {
      low: 0,
      showArea: false,
      showPoint: false,
      showLine: true,
      lineSmooth: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 10,
        bottom: 0,
        left: 10
      },
      axisX: {
        showLabel: true,
        showGrid: false,
        offset: 30
      },
      axisY: {
        showLabel: true,
        showGrid: true,
        offset: 30
      }
    });

    //char-linearea-two
    new Chartist.Line('#charLineareaTwo .ct-chart', {
      labels: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
      series: [
        [0, 2.5, 2, 2.8, 2.6, 3.8, 0],
        [0, 1.4, 0.5, 2, 1.2, 0.9, 0]
      ]
    }, {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 10,
        bottom: 0,
        left: 0
      },
      axisX: {
        showGrid: false,
        labelOffset: {
          x: -14,
          y: 0
        },
      },
      axisY: {
        labelOffset: {
          x: -10,
          y: 0
        },
        labelInterpolationFnc: function(num) {
          return num % 1 === 0 ? num : false;
        }
      }
    });

    //chart-linepoint
    new Chartist.Line("#chartLinepoint .ct-chart", {
      labels: ['1', '2', '3', '4', '5', '6'],
      series: [
        [1, 1.5, 0.5, 2, 2.5, 1.5]
      ]
    }, {
      low: 0,
      showArea: false,
      showPoint: true,
      showLine: true,
      fullWidth: true,
      lineSmooth: false,
      chartPadding: {
        top: 10,
        right: -4,
        bottom: 10,
        left: -4
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

    //chart-timeline-two
    var timeline_labels = [];
    var timeline_data1 = [];
    var timeline_data2 = [];
    var totalPoints = 20;
    var updateInterval = 1000;
    var now = new Date().getTime();

    function getData() {
      timeline_labels.shift();
      timeline_data1.shift();
      timeline_data2.shift();

      while (timeline_data1.length < totalPoints) {
        var x = Math.random() * 100 + 800;
        var y = Math.random() * 100 + 400;
        timeline_labels.push(now += updateInterval);
        timeline_data1.push(x);
        timeline_data2.push(y);
      }
    }

    var timlelineData = {
      labels: timeline_labels,
      series: [
        timeline_data1,
        timeline_data2
      ]
    };

    var timelineOptions = {
      low: 0,
      showArea: true,
      showPoint: false,
      showLine: false,
      fullWidth: true,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    };

    new Chartist.Line("#chartTimelineTwo .ct-chart", timlelineData, timelineOptions);

    function update() {
      getData();

      new Chartist.Line("#chartTimelineTwo .ct-chart", timlelineData, timelineOptions);
      setTimeout(update, updateInterval);
    }

    update();

    //chart-stacked-bar
    new Chartist.Bar('#chartStackedBar .ct-chart', {
      labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
      series: [
        [50, 90, 100, 90, 110, 100, 120, 130, 115, 95, 80, 85, 100, 140, 130, 120, 135, 110, 120, 105, 100, 105, 90, 110, 100, 60],
        [150, 190, 200, 190, 210, 200, 220, 230, 215, 195, 180, 185, 200, 240, 230, 220, 235, 210, 220, 205, 200, 205, 190, 210, 200, 160]
      ]
    }, {
      stackBars: true,
      fullWidth: true,
      seriesBarDistance: 0,
      chartPadding: {
        top: 0,
        right: 30,
        bottom: 30,
        left: 20
      },
      axisX: {
        showLabel: false,
        showGrid: false,
        offset: 0
      },
      axisY: {
        showLabel: false,
        showGrid: false,
        offset: 0
      }
    });

  });

})(document, window, jQuery);

(function(document, window, $) {
  'use strict';
  var Site = window.Site;
  // widget weather
  $(document).ready(function() {
    Site.run();

    var ex1_sunny = new Skycons({
      "color": "white"
    });
    ex1_sunny.set(document.getElementById("ex4-rain"), "rain");
    ex1_sunny.play();

    var ex5 = new Skycons({
        "color": "white"
      }),
      ex5_list = [
        "ex5-partly-cloudy",
        "ex5-sunny",
        "ex5-cloudy",
        "ex5-rain"
      ],
      ex5_type = [
        "partly-cloudy-day",
        "clear-day",
        "cloudy",
        "rain"
      ],
      ex5_i;

    for (ex5_i = ex5_list.length; ex5_i--;) {
      ex5.set(ex5_list[ex5_i], ex5_type[ex5_i]);
    }

    ex5.play();

    var ex6_snow = new Skycons({
      "color": "white"
    });
    ex6_snow.set(document.getElementById("ex6-snow"), "snow");
    ex6_snow.play();

    var ex7_sleet = new Skycons({
      "color": "#212121"
    });
    ex7_sleet.set(document.getElementById("ex7-sleet"), "sleet");
    ex7_sleet.play();
  });

})(document, window, jQuery);
