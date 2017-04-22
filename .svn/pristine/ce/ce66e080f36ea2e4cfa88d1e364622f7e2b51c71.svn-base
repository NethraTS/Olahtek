var gulp = require('gulp'),
    usemin = require('gulp-usemin'),
    wrap = require('gulp-wrap'),
   // connect = require('gulp-connect'),
    watch = require('gulp-watch'),
    uglify         = require('gulp-uglify');
    minifyCss = require('gulp-cssnano'),
    minifyJs = require('gulp-uglify'),
    concat = require('gulp-concat'),
    less = require('gulp-less'),
    rename = require('gulp-rename'),
    minifyHTML = require('gulp-htmlmin');
    var inject         = require('gulp-inject');
    var runSequence    = require('gulp-run-sequence');
    var gzip           = require('gulp-gzip');
    var clone          = require('gulp-clone');
    var series         = require('stream-series');

var paths = {
    scripts: 'WebContent/js/**/*.*',
    styles: 'WebContent/less/**/*.*',
    images: 'WebContent/img/*.*',
    templates: 'WebContent/templates/**/*.html',
    index: 'WebContent/index.html',
    vendor:'WebContent/vendor/**/*.*',
    bower_fonts: 'WebContent/components/**/*.{ttf,woff,eof,svg}',
    web_inf: 'WebContent/WEB-INF/**/*.*',
};

/**
 * Handle bower components from index
 */
gulp.task('usemin', function() {
    return gulp.src(paths.index)
        .pipe(usemin({
            js: [minifyJs(), 'concat'],
            css: [minifyCss({keepSpecialComments: 0}), 'concat'],
        }))
        .pipe(gulp.dest('WebContent/dist/'));
        
});

/**
 * Copy assets
 */
gulp.task('build-assets', ['copy-bower_fonts','copy-web-inf']);


gulp.task('copy-bower_fonts', function() {
    return gulp.src(paths.bower_fonts)
        .pipe(rename({
            dirname: '/fonts'
        }))
        .pipe(gulp.dest('WebContent/dist/'));
});

gulp.task('copy-web-inf', function() {
    return gulp.src(paths.web_inf)
    	.pipe(rename({
            dirname: '/WEB-INF'
        }))
        .pipe(gulp.dest('WebContent/dist/'));
});
/**
 * Handle custom files
 */
gulp.task('build-custom', ['custom-images', 'custom-js', 'custom-less', 'custom-templates','custom-vendor']);

gulp.task('custom-images', function() {
    return gulp.src(paths.images)
        .pipe(gulp.dest('WebContent/dist/img'));
});

gulp.task('custom-js', function() {
    return gulp.src(paths.scripts)
/*        .pipe(minifyJs())
        .pipe(concat('dashboard.min.js'))*/
        .pipe(gulp.dest('WebContent/dist/js'));
});

gulp.task('custom-less', function() {
    return gulp.src(paths.styles)
        .pipe(less())
        .pipe(gulp.dest('WebContent/dist/css'));
});

gulp.task('custom-templates', function() {
    return gulp.src(paths.templates)
        .pipe(minifyHTML())
        .pipe(gulp.dest('WebContent/dist/templates'));
});

gulp.task('custom-vendor', function() {
    return gulp.src(paths.vendor)
        .pipe(gulp.dest('WebContent/dist/vendor'));
});

/**
 * Watch custom files
 */
gulp.task('watch', function() {
    gulp.watch([paths.images], ['custom-images']);
    gulp.watch([paths.styles], ['custom-less']);
    gulp.watch([paths.scripts], ['custom-js']);
    gulp.watch([paths.templates], ['custom-templates']);
    gulp.watch([paths.vendor], ['custom-vendor']);
    gulp.watch([paths.index], ['usemin']);
});

/**
 * Live reload server
 */
/*gulp.task('webserver', function() {
    connect.server({
        root: 'dist',
        livereload: true,
        port: 8888
    });
});*/

//gulp.task('livereload', function() {
//    gulp.src(['WebContent/dist/**/*.*'])
//        .pipe(watch(['WebContent/dist/**/*.*']))
//        .pipe(connect.reload());
//});

/**
 * Gulp tasks
 */
//gulp.task('default', ['usemin', 'build-assets', 'build-custom']);
//gulp.task('default', ['build', 'webserver', 'livereload', 'watch']);


var vendorJs;
var vendorCss;
var components;
/* Dev section*/
gulp.task('lib-js-files', function () {
    vendorJs = gulp.src(paths.scripts)
    .pipe(gulp.dest('WebContent/js'));
   
});

gulp.task('component-js-files', function () {
	components = gulp.src('WebContent/lib/**/*.js')
    .pipe(gulp.dest('WebContent/lib'));
});
/* end of Dev script */

/* build for production */
gulp.task('lib-js-min-files', function () {
    vendorJs = gulp.src(paths.scripts)
    .pipe(concat('lib.min.js'))
    //.pipe(uglify())
    .pipe(gulp.dest('WebContent/dist/js'));
});



gulp.task('component-js-min-files', function () {
	components = gulp.src('WebContent/lib/**/*.js')
    .pipe(concat('component.min.js'))
   // .pipe(uglify())
    .pipe(gulp.dest('WebContent/dist/js'));
});

/* end of build for production */

gulp.task('index', function () {
    var target = gulp.src("WebContent/index.html");
    //var sources = gulp.src(['src/main/webapp/resources/js/*.js', 'src/main/webapp/resources/css/*.css'], {read: false});
    return target.pipe(inject(series(components,vendorJs), {relative: true}))
        .pipe(gulp.dest('WebContent'));
});

gulp.task('dev', function () {
    runSequence('component-js-files','lib-js-files','index');
});
gulp.task('default', function () {
    runSequence('component-js-files','lib-js-files','index');
});
/*gulp.task('deploy', function () {
    runSequence('lib-js-min-files','component-js-min-files','index');
});*/
/*gulp.task('default', function () {
    runSequence('component-js-min-files','lib-js-min-files','index');
});*/
