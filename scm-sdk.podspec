Pod::Spec.new do |spec|
    spec.name                     = 'scm-sdk'
    spec.version                  = '7.7.26'
    spec.homepage                 = 'https://github.servicechannel.com/ServiceChannel/sdk-android'
    spec.source       = { :http => "https://github.servicechannel.com/ServiceChannel/sdk-android/raw/master/scm-sdk/src/release/7.7.26/scm-sdk-7.7.26.zip" }
    spec.authors                  = 'Service channel mobile team'
    spec.license                  = 'https://opensource.org/licenses/Apache-2.0'
    spec.summary                  = 'Service channel mobile team'
    spec.vendored_frameworks      = "scmSdk.xcframework"
    spec.libraries                = "c++"
    spec.static_framework         = true
    spec.pod_target_xcconfig = { 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'arm64' }
    spec.user_target_xcconfig = { 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'arm64' }
    spec.ios.deployment_target = '11.0'
end
