(
Quarks.install("Bjorklund");
Quarks.install("BatLib");
// Quarks.install("https://github.com/jamshark70/ddwSnippets");
// Quarks.install("ddwSnippets");
)


Platform.userExtensionDir
Platform.systemExtensionDir

// https://github.com/supercollider/sc3-plugins/releases

// https://github.com/supercollider/supercollider/wiki/Installing-SuperCollider-on-Ubuntu-systems

/*
compiling class library...
	NumPrimitives = 679
	compiling dir: '/usr/share/SuperCollider/SCClassLibrary'
	compiling dir: '/usr/share/SuperCollider/Extensions'
	compiling dir: '/home/erik/.local/share/SuperCollider/Extensions'
*/

/*
erik@erik-N73SV / $ dpkg -L supercollider-dev | grep -i version
/usr/include/SuperCollider/SCVersion.txt

erik@erik-N73SV / $ cat /usr/include/SuperCollider/SCVersion.txt

cmake -DSC_PATH=**HeaderIncludeFileLocation** -DCMAKE_INSTALL_PREFIX=**PluginLocation** -DCMAKE_BUILD_TYPE=Release ..

cmake -DSC_PATH=/usr/include/SuperCollider -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE=Release ..

git clone supercollider
cmake -DSC_PATH=~/git/supercollider -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE=Release ..

