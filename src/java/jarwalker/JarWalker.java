import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarWalker
{

    public static void main( String[] args ) throws IOException {
        File file = new File(
                "C:\\dev\\code\\sas\\sapient\\branches\\sas-colombia\\sapient-ikernel-dist\\target\\assembly\\dist\\lib" );
        printJarname( file, "resources_es.properties" );
        System.out.println( "DONE........" );
    }

    private static void printJarname( File dir, String filename ) throws IOException {
        if( dir.isFile() ) {
            if( dir.getName().endsWith( ".jar" ) ) {
                System.out.println( "inspecting [" + dir.getName() + "]" );
                JarFile jar = new JarFile( dir );
                Enumeration<JarEntry> entries = jar.entries();
                while( entries.hasMoreElements() ) {
                    JarEntry jarEntry = (JarEntry) entries.nextElement();
                    if( jarEntry.getName().contains( (filename) ) ) {
                        System.out.println( "************" + dir.getName() + "**************** contains the file "
                                + jarEntry.getName() );
                    }
                }
            }
            return;
        }
        if( !dir.isDirectory() ) {
            return;
        }

        File[] files = dir.listFiles();
        for( File myFile : files ) {
            printJarname( myFile, filename );
        }
    }

}
